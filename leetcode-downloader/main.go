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
	ID            int    `json:"id"`
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
	// Load config
	config, err := loadConfig()
	if err != nil {
		log.Fatalf("Failed to load config: %v", err)
	}

	// Load queries
	problemsetQuery, submissionListQuery, _, err := loadQueries()
	if err != nil {
		log.Fatalf("Failed to load queries: %v", err)
	}

	// Parse cookies
	session, csrfToken, err := parseCookies()
	if err != nil {
		log.Fatalf("Failed to parse cookies: %v", err)
	}
	if config.LeetcodeSession != "" {
		session = config.LeetcodeSession
	}
	if config.CsrfToken != "" {
		csrfToken = config.CsrfToken
	}

	// Set up HTTP client
	transport := &http.Transport{
		Proxy: http.ProxyFromEnvironment,
	}
	httpClient := &http.Client{
		Transport: transport,
		Timeout:   30 * time.Second,
	}

	// Fetch solved problems
	problems, err := fetchSubmissions(httpClient, session, csrfToken, problemsetQuery)
	if err != nil {
		log.Fatalf("Failed to fetch submissions: %v", err)
	}
	if problems == nil || len(problems) == 0 {
		log.Printf("No solved problems found for the user")
		return // Exit gracefully or proceed to other tasks
	}

	// Process submissions (example, adjust based on your actual code)
	for _, problem := range problems {
		log.Printf("Processing problem: %s (%s)", problem.Title, problem.TitleSlug)
		submissions, err := fetchSubmissionList(httpClient, session, csrfToken, submissionListQuery, problem.TitleSlug)
		if err != nil {
			log.Printf("Failed to fetch submissions for %s: %v", problem.TitleSlug, err)
			continue
		}
		// Process submissions (basic logging for now)
		for _, submission := range submissions {
			log.Printf("Submission ID: %d, Lang: %s, Status: %s, Timestamp: %d",
				submission.ID, submission.Lang, submission.Status, submission.Timestamp)
			// TODO: Fetch submission details with submissionDetailsQuery or save to file
		}
	}

	log.Printf("Completed processing %d problems", len(problems))
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

	//if config.Username == "" {
	//	return nil, fmt.Errorf("username is required in config.json")
	//}

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

// fetchSubmissionList fetches submissions for a problem
func fetchSubmissionList(httpClient *http.Client, session, csrfToken, submissionListQuery, titleSlug string) ([]Submission, error) {
	client := graphql.NewClient("https://leetcode.com/graphql", graphql.WithHTTPClient(httpClient))
	ctx := context.Background()
	var submissions []Submission

	limit := limitPerRequest
	offset := 0

	for {
		req := graphql.NewRequest(submissionListQuery)
		variables := map[string]interface{}{
			"questionSlug": titleSlug,
			"limit":        limit,
			"offset":       offset,
		}
		req.Var("questionSlug", titleSlug)
		req.Var("limit", limit)
		req.Var("offset", offset)
		req.Header.Set("Cookie", fmt.Sprintf("LEETCODE_SESSION=%s; csrftoken=%s", session, csrfToken))
		req.Header.Set("X-CSRFToken", csrfToken)

		// Log query variables
		variablesJSON, _ := json.MarshalIndent(variables, "", "  ")
		log.Printf("Sending submissionListQuery for %s with variables: %s", titleSlug, variablesJSON)

		var submissionResp SubmissionListResponse
		if err := client.Run(ctx, req, &submissionResp); err != nil {
			return nil, fmt.Errorf("failed to fetch submissions for %s: %v", titleSlug, err)
		}

		// Log response
		respJSON, _ := json.MarshalIndent(submissionResp, "", "  ")
		log.Printf("SubmissionList response for %s: %s", titleSlug, respJSON)

		submissions = append(submissions, submissionResp.SubmissionList.Submissions...)

		if !submissionResp.SubmissionList.HasNext || len(submissionResp.SubmissionList.Submissions) == 0 {
			break
		}
		offset += limit
	}

	log.Printf("Fetched %d submissions for %s", len(submissions), titleSlug)
	return submissions, nil
}

// fetchSubmissions fetches solved problems from LeetCode
func fetchSubmissions(httpClient *http.Client, session, csrfToken string, problemsetQuery string) ([]Problem, error) {
	client := graphql.NewClient("https://leetcode.com/graphql", graphql.WithHTTPClient(httpClient))
	ctx := context.Background()
	var problems []Problem

	limit := limitPerRequest
	skip := 0

	for {
		req := graphql.NewRequest(problemsetQuery)
		variables := map[string]interface{}{
			"filters": map[string]interface{}{
				"filterCombineType": "ALL", // Changed to "ALL"
				"statusFilter": map[string]interface{}{
					"questionStatuses": []string{"SOLVED"}, // Reverted to "SOLVED"
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
		}
		req.Var("filters", variables["filters"])
		req.Var("limit", limit)
		req.Var("skip", skip)
		req.Var("searchKeyword", variables["searchKeyword"])
		req.Var("sortBy", variables["sortBy"])
		req.Var("categorySlug", variables["categorySlug"])
		req.Header.Set("Cookie", fmt.Sprintf("LEETCODE_SESSION=%s; csrftoken=%s", session, csrfToken))
		req.Header.Set("X-CSRFToken", csrfToken)

		// Log query variables and headers for debugging
		variablesJSON, _ := json.MarshalIndent(variables, "", "  ")
		log.Printf("Sending problemsetQuery with variables: %s", variablesJSON)
		log.Printf("Request headers: Cookie=%s, X-CSRFToken=%s",
			req.Header.Get("Cookie"), req.Header.Get("X-CSRFToken"))

		var problemResp ProblemsetResponse
		if err := client.Run(ctx, req, &problemResp); err != nil {
			return nil, fmt.Errorf("failed to fetch solved problems: %v", err)
		}

		// Log full response for debugging
		respJSON, _ := json.MarshalIndent(problemResp, "", "  ")
		log.Printf("Response: %s", respJSON)

		problems = append(problems, problemResp.ProblemsetQuestionListV2.Questions...)

		if !problemResp.ProblemsetQuestionListV2.HasMore || len(problemResp.ProblemsetQuestionListV2.Questions) == 0 {
			break
		}
		skip += limit
	}

	if len(problems) == 0 {
		log.Printf("No solved problems found for the user")
		return nil, nil // Allow program to continue
	}

	log.Printf("Fetched %d solved problems", len(problems))
	return problems, nil
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
	if !initResp.SubmissionList.HasNext {
		total = len(initResp.SubmissionList.Submissions)
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

		submissions := resp.SubmissionList.Submissions
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

		if !resp.SubmissionList.HasNext {
			bar.Finish()
			break
		}

		if resp.SubmissionList.LastKey != "" {
			lastKey = &resp.SubmissionList.LastKey
		}
		offset += limitPerRequest
		time.Sleep(1 * time.Second)
	}

	log.Printf("Total submissions fetched and saved for %s: %d", problemTitle, totalSubmissions)
	return totalSubmissions
}

func getSubmissionDetails(client *graphql.Client, submissionID int, query, session, csrfToken string) (string, error) {
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
