package main

import (
	"bytes"
	"context"
	"encoding/json"
	"fmt"
	"github.com/schollz/progressbar/v3"
	"github.com/vektah/gqlparser/v2/ast"
	_ "github.com/vektah/gqlparser/v2/ast"
	"github.com/vektah/gqlparser/v2/parser"
	"io"
	"log"
	"net/http"
	"os"
	"path/filepath"
	"regexp"
	"strings"
	"time"

	"github.com/machinebox/graphql"
)

const (
	outputDir       = "leetcode_submissions"
	limitPerRequest = 20
	queryFile       = "query.graphql"
	cookieFile      = "cookies.txt"
	defaultTimeout  = 10 * time.Second
	maxProblems     = 3
)

var (
	langExt = map[string]string{
		"cpp":        "cpp",
		"java":       "java",
		"python":     "py",
		"python3":    "py",
		"c":          "c",
		"csharp":     "cs",
		"javascript": "js",
		"ruby":       "rb",
		"swift":      "swift",
		"golang":     "go",
		"scala":      "scala",
		"kotlin":     "kt",
		"rust":       "rs",
		"php":        "php",
		"typescript": "ts",
		"racket":     "rkt",
		"erlang":     "erl",
		"elixir":     "ex",
		"bash":       "sh",
		"mysql":      "sql",
		"oracle":     "sql",
		"mssql":      "sql",
		"pandas":     "py",
		"postgresql": "sql",
	}
)

type Config struct {
	Username string `json:"username"`
}

type Problem struct {
	ID                 string `json:"id"`
	TitleSlug          string `json:"titleSlug"`
	Title              string `json:"title"`
	TranslatedTitle    string `json:"translatedTitle"`
	QuestionFrontendID string `json:"questionFrontendId"`
	PaidOnly           bool   `json:"paidOnly"`
	Difficulty         string `json:"difficulty"`
	Status             string `json:"status"`
}

type ProblemsetResponse struct {
	Data struct {
		ProblemsetQuestionListV2 struct {
			Questions []Problem `json:"questions"`
			HasMore   bool      `json:"hasMore"`
		} `json:"problemsetQuestionListV2"`
	} `json:"data"`
}

type Submission struct {
	ID            string `json:"id"`
	Title         string `json:"title"`
	TitleSlug     string `json:"titleSlug"`
	Status        int    `json:"status"`
	StatusDisplay string `json:"statusDisplay"`
	Lang          string `json:"lang"`
	LangName      string `json:"langName"`
	Runtime       string `json:"runtime"`
	Timestamp     string `json:"timestamp"`
	URL           string `json:"url"`
	IsPending     string `json:"isPending"`
	Memory        string `json:"memory"`
	HasNotes      bool   `json:"hasNotes"`
	Notes         string `json:"notes"`
}

type SubmissionListResponse struct {
	Data struct {
		QuestionSubmissionList struct {
			LastKey     string       `json:"lastKey"`
			HasNext     bool         `json:"hasNext"`
			Submissions []Submission `json:"submissions"`
		} `json:"questionSubmissionList"`
	} `json:"data"`
}

type SubmissionDetailsResponse struct {
	Data struct {
		SubmissionDetails struct {
			Code       string `json:"code"`
			Runtime    string `json:"runtime"`
			Memory     string `json:"memory"`
			StatusCode int    `json:"statusCode"`
			Lang       struct {
				Name        string `json:"name"`
				VerboseName string `json:"verboseName"`
			} `json:"lang"`
			Question struct {
				Title     string `json:"title"`
				TitleSlug string `json:"titleSlug"`
			} `json:"question"`
			Timestamp string `json:"timestamp"`
		} `json:"submissionDetails"`
	} `json:"data"`
}

func main() {
	log.SetFlags(log.LstdFlags | log.Lshortfile)

	config, err := loadConfig()
	if err != nil {
		log.Fatalf("Failed to load config: %v", err)
	}

	session, csrfToken, err := parseCookies()
	if err != nil {
		log.Fatalf("Failed to parse cookies: %v", err)
	}

	problemsetQuery, submissionListQuery, submissionDetailsQuery, err := loadQueries()
	if err != nil {
		log.Fatalf("Failed to load queries: %v", err)
	}

	if err := os.MkdirAll(outputDir, 0755); err != nil {
		log.Fatalf("Failed to create output directory: %v", err)
	}

	fetchSubmissions(config, session, csrfToken, problemsetQuery, submissionListQuery, submissionDetailsQuery)
}

func loadConfig() (*Config, error) {
	data, err := os.ReadFile("config.json")
	if err != nil {
		return nil, fmt.Errorf("config.json not found: %w", err)
	}

	var config Config
	if err := json.Unmarshal(data, &config); err != nil {
		return nil, fmt.Errorf("failed to parse config.json: %w", err)
	}

	if config.Username == "" {
		return nil, fmt.Errorf("username is required in config.json")
	}

	return &config, nil
}

func parseCookies() (string, string, error) {
	data, err := os.ReadFile(cookieFile)
	if err != nil {
		return "", "", fmt.Errorf("cookies.txt not found: %w", err)
	}

	lines := strings.Split(string(data), "\n")
	var session, csrfToken string
	for _, line := range lines {
		line = strings.TrimSpace(line)
		if line == "" || strings.HasPrefix(line, "#") {
			continue
		}
		parts := strings.Fields(line)
		if len(parts) < 7 {
			continue
		}
		domain, name, value := parts[0], parts[5], parts[6]
		if domain != ".leetcode.com" && domain != "leetcode.com" {
			continue
		}
		if name == "LEETCODE_SESSION" {
			session = value
		} else if name == "csrftoken" {
			csrfToken = value
		}
	}

	if session == "" {
		return "", "", fmt.Errorf("LEETCODE_SESSION not found in cookies.txt")
	}
	if csrfToken == "" {
		return "", "", fmt.Errorf("csrftoken not found in cookies.txt")
	}

	return session, csrfToken, nil
}
func loadQueries() (string, string, string, error) {
	data, err := os.ReadFile(queryFile)
	if err != nil {
		return "", "", "", fmt.Errorf("query.graphql not found: %w", err)
	}

	content := string(data)
	// Parse GraphQL file into AST without schema validation
	doc, gqlErr := parser.ParseQuery(&ast.Source{Name: "query.graphql", Input: content})
	if gqlErr != nil {
		return "", "", "", fmt.Errorf("failed to parse query.graphql: %v", gqlErr)
	}

	// Extract queries by name
	queries := make(map[string]string)
	for _, op := range doc.Operations {
		if op.Operation != "query" || op.Name == "" {
			continue
		}
		// Extract query text using Position
		if op.Position == nil {
			log.Printf("Warning: No position data for query %s", op.Name)
			continue
		}
		// Use Start and find end position
		start := op.Position.Start
		// Find end by searching for the next operation or end of file
		end := len(content)
		for _, nextOp := range doc.Operations {
			if nextOp.Position != nil && nextOp.Position.Start > start && nextOp.Position.Start < end {
				end = nextOp.Position.Start
			}
		}
		queryText := strings.TrimSpace(content[start:end])
		queries[op.Name] = queryText
		log.Printf("Parsed query %s: %s", op.Name, queryText)
	}

	problemsetQuery, ok := queries["problemsetQuestionListV2"]
	if !ok {
		return "", "", "", fmt.Errorf("problemsetQuestionListV2 query not found")
	}
	submissionListQuery, ok := queries["submissionList"]
	if !ok {
		return "", "", "", fmt.Errorf("submissionList query not found")
	}
	submissionDetailsQuery, ok := queries["submissionDetails"]
	if !ok {
		return "", "", "", fmt.Errorf("submissionDetails query not found")
	}

	return problemsetQuery, submissionListQuery, submissionDetailsQuery, nil
}

func fetchSubmissions(config *Config, session, csrfToken, problemsetQuery, submissionListQuery, submissionDetailsQuery string) {
	// Create a custom HTTP client with timeout
	httpClient := &http.Client{
		Timeout: defaultTimeout,
	}
	client := graphql.NewClient("https://leetcode.com/graphql", graphql.WithHTTPClient(httpClient))

	// Step 1: Get solved problems
	var problemResp ProblemsetResponse
	problemReq := graphql.NewRequest(problemsetQuery)
	problemReq.Var("filters", map[string]interface{}{
		"filterCombineType": "ALL",
		"statusFilter": map[string]interface{}{
			"questionStatuses": []string{"SOLVED"},
			"operator":         "IS",
		},
		"difficultyFilter": map[string]interface{}{
			"difficulties": []string{},
			"operator":     "IS",
		},
		"languageFilter": map[string]interface{}{
			"languageSlugs": []string{},
			"operator":      "IS",
		},
		"topicFilter": map[string]interface{}{
			"topicSlugs": []string{},
			"operator":   "IS",
		},
		"acceptanceFilter":    map[string]interface{}{},
		"frequencyFilter":     map[string]interface{}{},
		"lastSubmittedFilter": map[string]interface{}{},
		"publishedFilter":     map[string]interface{}{},
		"companyFilter":       map[string]interface{}{"companySlugs": []string{}, "operator": "IS"},
		"positionFilter":      map[string]interface{}{"positionSlugs": []string{}, "operator": "IS"},
		"premiumFilter":       map[string]interface{}{"premiumStatus": []string{}, "operator": "IS"},
	})
	problemReq.Var("limit", 100)
	problemReq.Var("searchKeyword", "")
	problemReq.Var("skip", 0)
	problemReq.Var("sortBy", map[string]string{"sortField": "CUSTOM", "sortOrder": "ASCENDING"})
	problemReq.Var("categorySlug", "all-code-essentials")
	problemReq.Header.Set("Cookie", fmt.Sprintf("LEETCODE_SESSION=%s; csrftoken=%s", session, csrfToken))
	problemReq.Header.Set("X-CSRFToken", csrfToken)
	problemReq.Header.Set("Content-Type", "application/json")
	problemReq.Header.Set("Referer", "https://leetcode.com")
	problemReq.Header.Set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")

	ctx := context.Background()
	if err := client.Run(ctx, problemReq, &problemResp); err != nil {
		log.Printf("Failed to fetch solved problems: %v", err)
		return
	}

	solvedProblems := problemResp.Data.ProblemsetQuestionListV2.Questions
	if len(solvedProblems) == 0 {
		log.Println("No solved problems found for the user")
		return
	}

	// Select up to maxProblems
	selectedProblems := solvedProblems
	if len(selectedProblems) > maxProblems {
		selectedProblems = selectedProblems[:maxProblems]
	}
	log.Printf("Selected %d problem(s): %v", len(selectedProblems), selectedProblems)

	// Step 2: Fetch submissions for each problem
	totalSubmissionsAll := 0
	for _, problem := range selectedProblems {
		totalSubmissionsAll += fetchSubmissionsForProblem(client, problem.TitleSlug, problem.Title, session, csrfToken, submissionListQuery, submissionDetailsQuery)
	}

	log.Printf("Total submissions fetched and saved across all problems: %d", totalSubmissionsAll)
	log.Println("Fetch complete!")
}

func fetchSubmissionsForProblem(client *graphql.Client, questionSlug, problemTitle, session, csrfToken, submissionListQuery, submissionDetailsQuery string) int {
	offset := 0
	totalSubmissions := 0
	var lastKey *string
	ctx := context.Background()

	// Initial request to estimate total
	var initResp SubmissionListResponse
	initReq := graphql.NewRequest(submissionListQuery)
	initReq.Var("offset", 0)
	initReq.Var("limit", 1)
	initReq.Var("questionSlug", questionSlug)
	initReq.Var("status", 10)
	initReq.Header.Set("Cookie", fmt.Sprintf("LEETCODE_SESSION=%s; csrftoken=%s", session, csrfToken))
	initReq.Header.Set("X-CSRFToken", csrfToken)
	initReq.Header.Set("Content-Type", "application/json")
	initReq.Header.Set("Referer", "https://leetcode.com")
	initReq.Header.Set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")

	if err := client.Run(ctx, initReq, &initResp); err != nil {
		log.Printf("Failed to initialize submissions for %s: %v", questionSlug, err)
		return 0
	}

	total := 9999
	if !initResp.Data.QuestionSubmissionList.HasNext {
		total = len(initResp.Data.QuestionSubmissionList.Submissions)
	}

	bar := progressbar.NewOptions(total,
		progressbar.OptionSetDescription(fmt.Sprintf("Fetching submissions for %s", problemTitle)),
		progressbar.OptionSetItsString("submission"),
		progressbar.OptionShowCount(),
		progressbar.OptionShowIts())

	for {
		var resp SubmissionListResponse
		req := graphql.NewRequest(submissionListQuery)
		req.Var("offset", offset)
		req.Var("limit", limitPerRequest)
		if lastKey != nil {
			req.Var("lastKey", *lastKey)
		}
		req.Var("questionSlug", questionSlug)
		req.Var("status", 10)
		req.Header.Set("Cookie", fmt.Sprintf("LEETCODE_SESSION=%s; csrftoken=%s", session, csrfToken))
		req.Header.Set("X-CSRFToken", csrfToken)
		req.Header.Set("Content-Type", "application/json")
		req.Header.Set("Referer", "https://leetcode.com")
		req.Header.Set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")

		if err := client.Run(ctx, req, &resp); err != nil {
			log.Printf("Error at offset %d for %s: %v", offset, questionSlug, err)
			bar.Finish()
			break
		}

		submissions := resp.Data.QuestionSubmissionList.Submissions
		if len(submissions) == 0 {
			bar.Finish()
			break
		}

		for _, submission := range submissions {
			code, err := getSubmissionDetails(client, submission.ID, submissionDetailsQuery, session, csrfToken)
			if err != nil {
				log.Printf("Failed to fetch details for submission %s: %v", submission.ID, err)
				code = "// Code not available"
			}

			if err := saveSubmission(submission, code); err != nil {
				log.Printf("Failed to save submission %s: %v", submission.ID, err)
			}

			totalSubmissions++
			bar.Add(1)
			time.Sleep(500 * time.Millisecond)
		}

		if !resp.Data.QuestionSubmissionList.HasNext {
			bar.Finish()
			break
		}

		if resp.Data.QuestionSubmissionList.LastKey != "" {
			lastKey = &resp.Data.QuestionSubmissionList.LastKey
		}
		offset += limitPerRequest
		time.Sleep(1 * time.Second)
	}

	log.Printf("Total submissions fetched and saved for %s: %d", problemTitle, totalSubmissions)
	return totalSubmissions
}

func getSubmissionDetails(client *graphql.Client, submissionID string, query, session, csrfToken string) (string, error) {
	var resp SubmissionDetailsResponse
	req := graphql.NewRequest(query)
	req.Var("submissionId", submissionID)
	req.Header.Set("Cookie", fmt.Sprintf("LEETCODE_SESSION=%s; csrftoken=%s", session, csrfToken))
	req.Header.Set("X-CSRFToken", csrfToken)
	req.Header.Set("Content-Type", "application/json")
	req.Header.Set("Referer", "https://leetcode.com")
	req.Header.Set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")

	ctx := context.Background()
	if err := client.Run(ctx, req, &resp); err != nil {
		return "", fmt.Errorf("failed to fetch submission details: %w", err)
	}

	return resp.Data.SubmissionDetails.Code, nil
}

func saveSubmission(submission Submission, code string) error {
	lang := strings.ToLower(submission.LangName)
	timestamp, err := time.Parse("2006-01-02 15:04:05", submission.Timestamp)
	if err != nil {
		timestamp = time.Now()
	}

	ext := langExt[lang]
	if ext == "" {
		ext = "txt"
	}

	safeTitle := regexp.MustCompile(`[^a-zA-Z0-9-_ ]`).ReplaceAllString(submission.Title, "")
	safeTitle = strings.ReplaceAll(strings.TrimSpace(safeTitle), " ", "_")
	baseFilename := fmt.Sprintf("%s_%s", safeTitle, timestamp.Format("20060102_150405"))
	filePath := filepath.Join(outputDir, baseFilename+"."+ext)

	counter := 1
	for _, err := os.Stat(filePath); err == nil; _, err = os.Stat(filePath) {
		filePath = filepath.Join(outputDir, fmt.Sprintf("%s_%d.%s", baseFilename, counter, ext))
		counter++
	}

	file, err := os.Create(filePath)
	if err != nil {
		return fmt.Errorf("failed to create file %s: %w", filePath, err)
	}
	defer file.Close()

	var buf bytes.Buffer
	fmt.Fprintf(&buf, "// Problem: %s\n", submission.Title)
	fmt.Fprintf(&buf, "// Language: %s\n", lang)
	fmt.Fprintf(&buf, "// Timestamp: %s\n", timestamp)
	fmt.Fprintf(&buf, "// ID: %s\n", submission.ID)
	fmt.Fprintf(&buf, "// Runtime: %s\n", submission.Runtime)
	fmt.Fprintf(&buf, "// Memory: %s\n\n", submission.Memory)
	fmt.Fprintf(&buf, "%s", code)

	if _, err := io.Copy(file, &buf); err != nil {
		return fmt.Errorf("failed to write to file %s: %w", filePath, err)
	}

	log.Printf("Saved: %s", filePath)
	return nil
}
