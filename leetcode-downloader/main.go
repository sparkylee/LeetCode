package main

import (
	"bytes"
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
)

const (
	outputDir       = "leetcode_submissions"
	limitPerRequest = 20
	queryFile       = "query.graphql"
	cookieFile      = "leetcode.com_cookies.txt"
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
	LeetcodeSession string `json:"LEETCODE_SESSION"`
	CsrfToken       string `json:"csrftoken"`
}

type Problem struct {
	ID                 int    `json:"id"` // Changed from string to int
	TitleSlug          string `json:"titleSlug"`
	Title              string `json:"title"`
	TranslatedTitle    string `json:"translatedTitle"`
	QuestionFrontendID string `json:"questionFrontendId"`
	PaidOnly           bool   `json:"paidOnly"`
	Difficulty         string `json:"difficulty"`
	TopicTags          []struct {
		Name           string `json:"name"`
		Slug           string `json:"slug"`
		NameTranslated string `json:"nameTranslated"`
	} `json:"topicTags"`
	Status          string  `json:"status"`
	IsInMyFavorites bool    `json:"isInMyFavorites"`
	Frequency       float64 `json:"frequency"`
	AcRate          float64 `json:"acRate"`
}

type ProblemsetResponse struct {
	ProblemsetQuestionListV2 struct {
		Questions      []Problem `json:"questions"`
		TotalLength    int       `json:"totalLength"`
		FinishedLength int       `json:"finishedLength"`
		HasMore        bool      `json:"hasMore"`
	} `json:"problemsetQuestionListV2"`
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
	SubmissionList struct {
		Submissions []Submission `json:"submissions"`
		HasNext     bool         `json:"hasNext"`
		LastKey     string       `json:"lastKey"` // Added back
	} `json:"submissionList"`
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
	// Add a flag for direct titleSlug input
	var testSlug *string = new(string)

	// Load queries
	problemsetQuery, submissionListQuery, submissionDetailsQuery, err := loadQueries()
	if err != nil {
		log.Fatalf("Failed to load queries: %v", err)
	}

	// Parse cookies
	session, csrfToken, err := parseCookies()
	if err != nil {
		log.Fatalf("Failed to parse cookies: %v", err)
	}

	// Set up HTTP client
	transport := &http.Transport{
		Proxy: http.ProxyFromEnvironment,
	}
	httpClient := &http.Client{
		Transport: transport,
		Timeout:   30 * time.Second,
	}

	// If testSlug is provided, fetch and print submissions for that slug, then exit
	if *testSlug != "" {
		count := fetchSubmissionsForProblem(httpClient, *testSlug, *testSlug, session, csrfToken, submissionListQuery, submissionDetailsQuery)
		log.Printf("Processed %d submissions for %s", count, *testSlug)
		return
	}

	// Fetch solved problems
	problems, err := fetchSolvedProblemList(httpClient, session, csrfToken, problemsetQuery, maxProblems)
	if err != nil {
		log.Fatalf("Failed to fetch submissions: %v", err)
	}
	if problems == nil || len(problems) == 0 {
		log.Printf("No solved problems found for the user")
		return
	}

	// Process each problem's submissions
	totalProcessed := 0
	for _, problem := range problems {
		log.Printf("Processing problem: %s (%s)", problem.Title, problem.TitleSlug)
		count := fetchSubmissionsForProblem(httpClient, problem.TitleSlug, problem.Title, session, csrfToken, submissionListQuery, submissionDetailsQuery)
		totalProcessed += count
	}

	log.Printf("Completed processing %d problems with %d total submissions", len(problems), totalProcessed)
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

type GraphQLRequest struct {
	Query         string                 `json:"query"`
	Variables     map[string]interface{} `json:"variables"`
	OperationName string                 `json:"operationName"`
}

func makeGraphQLRequest[T any](httpClient *http.Client, session, csrfToken string, request *GraphQLRequest) (*T, error) {
	bodyBytes, err := json.Marshal(request)
	if err != nil {
		return nil, fmt.Errorf("failed to marshal request body: %v", err)
	}
	log.Printf("Request body: %s", string(bodyBytes))

	req, err := http.NewRequest("POST", "https://leetcode.com/graphql", bytes.NewBuffer(bodyBytes))
	if err != nil {
		return nil, fmt.Errorf("failed to create request: %v", err)
	}

	// Set common headers
	req.Header.Set("Cookie", fmt.Sprintf("LEETCODE_SESSION=%s; csrftoken=%s", session, csrfToken))
	req.Header.Set("X-CSRFToken", csrfToken)
	req.Header.Set("Content-Type", "application/json")
	req.Header.Set("Referer", "https://leetcode.com")
	req.Header.Set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")

	resp, err := httpClient.Do(req)
	if err != nil {
		return nil, fmt.Errorf("failed to execute request: %v", err)
	}
	defer resp.Body.Close()

	bodyBytes, err = io.ReadAll(resp.Body)
	if err != nil {
		return nil, fmt.Errorf("failed to read response body: %v", err)
	}
	log.Printf("Response body: %s", string(bodyBytes))

	// Create a new reader with the bytes for decoding the JSON
	resp.Body = io.NopCloser(bytes.NewBuffer(bodyBytes))

	var response T
	if err := json.NewDecoder(resp.Body).Decode(&response); err != nil {
		return nil, fmt.Errorf("failed to decode response: %v", err)
	}

	return &response, nil
}

func fetchSubmissionListForProblem(httpClient *http.Client, session, csrfToken, submissionListQuery, titleSlug string) ([]Submission, error) {
	var submissions []Submission
	limit := limitPerRequest
	offset := 0

	for {
		request := &GraphQLRequest{
			Query: submissionListQuery,
			Variables: map[string]interface{}{
				"offset":       offset,
				"limit":        limit,
				"lastKey":      nil,
				"questionSlug": titleSlug,
				"lang":         nil,
				"status":       10,
			},
			OperationName: "submissionList",
		}

		type Response struct {
			Data struct {
				QuestionSubmissionList struct {
					LastKey     string       `json:"lastKey"`
					HasNext     bool         `json:"hasNext"`
					Submissions []Submission `json:"submissions"`
				} `json:"questionSubmissionList"`
			} `json:"data"`
		}

		response, err := makeGraphQLRequest[Response](httpClient, session, csrfToken, request)
		if err != nil {
			return nil, err
		}

		submissions = append(submissions, response.Data.QuestionSubmissionList.Submissions...)

		if !response.Data.QuestionSubmissionList.HasNext || len(response.Data.QuestionSubmissionList.Submissions) == 0 {
			break
		}
		offset += limit
	}

	log.Printf("Fetched %d submissions for %s", len(submissions), titleSlug)
	return submissions, nil
}

func fetchSolvedProblemList(httpClient *http.Client, session, csrfToken string, problemsetQuery string, maxCount int) ([]Problem, error) {
	var problems []Problem
	limit := limitPerRequest
	skip := 0

	for {
		request := &GraphQLRequest{
			Query: problemsetQuery,
			Variables: map[string]interface{}{
				"filters": map[string]interface{}{
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
				},
				"limit":         limit,
				"skip":          skip,
				"searchKeyword": "",
				"sortBy":        nil,
				"categorySlug":  "all-code-essentials",
			},
			OperationName: "problemsetQuestionListV2",
		}

		type Response struct {
			Data struct {
				ProblemsetQuestionListV2 struct {
					Questions  []Problem `json:"questions"`
					TotalCount int       `json:"totalCount"`
					HasMore    bool      `json:"hasMore"`
				} `json:"problemsetQuestionListV2"`
			} `json:"data"`
		}

		response, err := makeGraphQLRequest[Response](httpClient, session, csrfToken, request)
		if err != nil {
			return nil, err
		}

		problems = append(problems, response.Data.ProblemsetQuestionListV2.Questions...)

		// Stop if we've reached or exceeded maxCount (when maxCount > 0)
		if maxCount > 0 && len(problems) >= maxCount {
			problems = problems[:maxCount] // Trim to exact maxCount
			break
		}

		if !response.Data.ProblemsetQuestionListV2.HasMore || len(response.Data.ProblemsetQuestionListV2.Questions) == 0 {
			break
		}
		skip += limit
	}

	log.Printf("Fetched %d solved problems", len(problems))
	return problems, nil
}
func fetchSubmissionsForProblem(httpClient *http.Client, questionSlug, problemTitle, session, csrfToken, submissionListQuery, submissionDetailsQuery string) int {
	// Get the list of submissions
	submissions, err := fetchSubmissionListForProblem(httpClient, session, csrfToken, submissionListQuery, questionSlug)
	if err != nil {
		log.Printf("Failed to fetch submission list for %s: %v", questionSlug, err)
		return 0
	}

	total := len(submissions)
	totalSubmissions := 0

	bar := progressbar.NewOptions(total,
		progressbar.OptionSetDescription(fmt.Sprintf("Fetching submissions for %s", problemTitle)),
		progressbar.OptionSetItsString("submission"),
		progressbar.OptionShowCount(),
		progressbar.OptionShowIts())

	for _, submission := range submissions {
		code, err := getSubmissionDetails(httpClient, submission.ID, submissionDetailsQuery, session, csrfToken)
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

	bar.Finish()
	log.Printf("Total submissions fetched and saved for %s: %d", problemTitle, totalSubmissions)
	return totalSubmissions
}
func getSubmissionDetails(httpClient *http.Client, submissionID, query, session, csrfToken string) (string, error) {
	request := &GraphQLRequest{
		Query: query,
		Variables: map[string]interface{}{
			"submissionId": submissionID,
		},
		OperationName: "submissionDetails",
	}

	response, err := makeGraphQLRequest[SubmissionDetailsResponse](httpClient, session, csrfToken, request)
	if err != nil {
		return "", fmt.Errorf("failed to fetch submission details: %w", err)
	}

	return response.Data.SubmissionDetails.Code, nil
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
