import requests
import os
import json
import time
from datetime import datetime
from tqdm import tqdm
import logging
from http.cookiejar import MozillaCookieJar
from graphql import parse, DocumentNode
from graphql.language import OperationDefinitionNode

# Setup logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

# Configuration
OUTPUT_DIR = "leetcode_submissions"
LIMIT_PER_REQUEST = 20
QUERY_FILE = "../query.graphql"
COOKIE_FILE = "../cookies.txt"

# Language extension mapping
LANG_EXT = {
    "cpp": "cpp", "java": "java", "python": "py", "python3": "py", "c": "c",
    "csharp": "cs", "javascript": "js", "ruby": "rb", "swift": "swift", "golang": "go",
    "scala": "scala", "kotlin": "kt", "rust": "rs", "php": "php", "typescript": "ts",
    "racket": "rkt", "erlang": "erl", "elixir": "ex", "bash": "sh", "mysql": "sql",
    "oracle": "sql", "mssql": "sql", "pandas": "py", "postgresql": "sql"
}

# Load configuration from config.json
try:
    with open("../config.json", "r") as f:
        config = json.load(f)
    USERNAME = config["username"]
    QUESTION_SLUG = config["questionSlug"]
except FileNotFoundError:
    logger.error("config.json not found. Please create it with username and questionSlug.")
    exit(1)
except KeyError as e:
    logger.error(f"Missing key in config.json: {e}")
    exit(1)

# Parse cookies from cookies.txt
def parse_cookies():
    cookie_jar = MozillaCookieJar()
    try:
        cookie_jar.load(COOKIE_FILE, ignore_discard=True, ignore_expires=True)
    except FileNotFoundError:
        logger.error(f"{COOKIE_FILE} not found. Please export cookies from your browser.")
        exit(1)
    except Exception as e:
        logger.error(f"Failed to parse {COOKIE_FILE}: {e}")
        exit(1)

    leetcode_session = None
    csrf_token = None
    for cookie in cookie_jar:
        if cookie.domain in (".leetcode.com", "leetcode.com"):
            if cookie.name == "LEETCODE_SESSION":
                leetcode_session = cookie.value
            elif cookie.name == "csrftoken":
                csrf_token = cookie.value

    if not leetcode_session:
        logger.error("LEETCODE_SESSION not found in cookies.txt.")
        exit(1)
    if not csrf_token:
        logger.error("csrftoken not found in cookies.txt.")
        exit(1)

    return leetcode_session, csrf_token

# Load and parse GraphQL queries from file using graphql-core
def load_queries():
    try:
        with open(QUERY_FILE, "r") as f:
            content = f.read()
    except FileNotFoundError:
        logger.error(f"{QUERY_FILE} not found. Please create it with the GraphQL queries.")
        exit(1)

    try:
        document = parse(content)
    except Exception as e:
        logger.error(f"Failed to parse {QUERY_FILE}: {e}")
        exit(1)

    queries = {}
    for definition in document.definitions:
        if isinstance(definition, OperationDefinitionNode) and definition.operation.value == "query":
            query_name = definition.name.value if definition.name else None
            if query_name:
                query_str = content[definition.loc.start:definition.loc.end].strip()
                queries[query_name] = query_str

    if "submissionList" not in queries:
        logger.error("submissionList query not found in query.graphql.")
        exit(1)
    if "submissionDetails" not in queries:
        logger.error("submissionDetails query not found in query.graphql.")
        exit(1)

    return queries["submissionList"], queries["submissionDetails"]

# Load cookies and queries
try:
    LEETCODE_SESSION, CSRF_TOKEN = parse_cookies()
except Exception as e:
    logger.error(f"Error loading cookies: {e}")
    exit(1)

try:
    SUBMISSION_LIST_QUERY, SUBMISSION_DETAILS_QUERY = load_queries()
except Exception as e:
    logger.error(f"Error loading queries: {e}")
    exit(1)

# Create output directory
if not os.path.exists(OUTPUT_DIR):
    os.makedirs(OUTPUT_DIR)

# Headers for API requests
headers = {
    "Cookie": f"LEETCODE_SESSION={LEETCODE_SESSION}; csrftoken={CSRF_TOKEN}",
    "X-CSRFToken": CSRF_TOKEN,
    "Content-Type": "application/json",
    "Referer": "https://leetcode.com",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
}

# Fetch submissionList using questionSubmissionList query
def get_submission_list(offset, limit, last_key=None):
    query = {
        "query": SUBMISSION_LIST_QUERY,
        "operationName": "submissionList",
        "variables": {
            "offset": offset,
            "limit": limit,
            "lastKey": last_key,
            "questionSlug": QUESTION_SLUG,
            "lang": None,
            "status": 10
        }
    }
    try:
        response = requests.post("https://leetcode.com/graphql", json=query, headers=headers, timeout=10)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.HTTPError as e:
        logger.error(f"HTTP error in submissionList: {e.response.status_code} - {e.response.text}")
        raise
    except requests.exceptions.RequestException as e:
        logger.error(f"Network error in submissionList: {e}")
        raise

# Fetch submission details (including code) using submissionDetails query
def get_submission_details(submission_id):
    query = {
        "query": SUBMISSION_DETAILS_QUERY,
        "operationName": "submissionDetails",
        "variables": {
            "submissionId": submission_id
        }
    }
    try:
        response = requests.post("https://leetcode.com/graphql", json=query, headers=headers, timeout=10)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.HTTPError as e:
        logger.error(f"HTTP error in submissionDetails for ID {submission_id}: {e.response.status_code} - {e.response.text}")
        return None
    except requests.exceptions.RequestException as e:
        logger.error(f"Network error in submissionDetails for ID {submission_id}: {e}")
        return None

# Save submission to file
def save_submission(submission, code):
    title = submission["title"]
    lang = submission["langName"].lower()
    timestamp = submission["timestamp"]

    ext = LANG_EXT.get(lang, "txt")
    safe_title = "".join(c for c in title if c.isalnum() or c in " -_").strip().replace(" ", "_")
    base_filename = f"{safe_title}_{datetime.fromtimestamp(int(timestamp)).strftime('%Y%m%d_%H%M%S')}"
    filepath = os.path.join(OUTPUT_DIR, f"{base_filename}.{ext}")

    counter = 1
    while os.path.exists(filepath):
        filepath = os.path.join(OUTPUT_DIR, f"{base_filename}_{counter}.{ext}")
        counter += 1

    try:
        with open(filepath, "w", encoding="utf-8") as f:
            f.write(f"// Problem: {title}\n")
            f.write(f"// Language: {lang}\n")
            f.write(f"// Timestamp: {datetime.fromtimestamp(int(timestamp))}\n")
            f.write(f"// ID: {submission['id']}\n")
            f.write(f"// Runtime: {submission['runtime']}\n")
            f.write(f"// Memory: {submission['memory']}\n\n")
            f.write(code if code else "// Code not available\n")
        logger.info(f"Saved: {os.path.basename(filepath)}")
    except IOError as e:
        logger.error(f"Failed to save {safe_title}: {e}")

# Main function to fetch and save submissions
def fetch_submissions():
    offset = 0
    total_submissions = 0
    last_key = None

    try:
        data = get_submission_list(0, 1, last_key)
        submissions = data["data"]["questionSubmissionList"]["submissions"]
        has_next = data["data"]["questionSubmissionList"]["hasNext"]
        total = 9999 if has_next else len(submissions)
    except Exception as e:
        logger.error(f"Failed to initialize: {e}")
        return

    with tqdm(total=total, desc="Fetching submissions", unit="submission") as pbar:
        while True:
            try:
                data = get_submission_list(offset, LIMIT_PER_REQUEST, last_key)
                submission_list = data["data"]["questionSubmissionList"]
                submissions = submission_list["submissions"]
                last_key = submission_list["lastKey"]
                has_next = submission_list["hasNext"]

                if not submissions:
                    break

                for submission in submissions:
                    submission_id = submission["id"]
                    details_data = get_submission_details(submission_id)
                    code = None
                    if details_data and "data" in details_data and "submissionDetails" in details_data["data"]:
                        code = details_data["data"]["submissionDetails"].get("code", "")

                    save_submission(submission, code)
                    total_submissions += 1
                    pbar.update(1)
                    time.sleep(0.5)

                if not has_next:
                    break

                offset += LIMIT_PER_REQUEST
                time.sleep(1)
            except Exception as e:
                logger.error(f"Error at offset {offset}: {e}")
                break

    logger.info(f"Total submissions fetched and saved: {total_submissions}")
    logger.info("Fetch complete!")

if __name__ == "__main__":
    fetch_submissions()