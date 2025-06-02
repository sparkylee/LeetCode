package main

import (
	"fmt"
	"github.com/schollz/progressbar/v3"
	"io"
	"leetcode/pkg/utils"
	"log"
	"net/http"
	"os"
	"path/filepath"
	"strings"
	"time"
)

const (
	limitPerRequest = 20
	queryFile       = "query.graphql"
	cookieFile      = "leetcode.com_cookies.txt"
	maxProblems     = -1
)

var (
	problemsetQuery        string
	submissionListQuery    string
	submissionDetailsQuery string
)

var (
	langExt = map[string]string{
		"cpp":        "cpp",
		"c++":        "cpp",
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

type SubmissionDetailsResponse struct {
	Data struct {
		SubmissionDetails struct {
			Code       string `json:"code"`
			Runtime    int    `json:"runtime"`
			Memory     int    `json:"memory"`
			StatusCode int    `json:"statusCode"`
			Lang       struct {
				Name        string `json:"name"`
				VerboseName string `json:"verboseName"`
			} `json:"lang"`
			Question struct {
				Title     string `json:"title"`
				TitleSlug string `json:"titleSlug"`
			} `json:"question"`
			Timestamp int `json:"timestamp"`
		} `json:"submissionDetails"`
	} `json:"data"`
}

func handleDebugMode(debugConfig *utils.DebugConfig, httpClient *http.Client, session, csrfToken string) bool {
	// Load debug config
	if debugConfig == nil {
		log.Println("No debug config found, running in normal mode")
		return false
	}
	// If testSubmissionID is provided, fetch and print submission details
	if debugConfig.TestSubmissionID != "" {
		code, err := getSubmissionDetails(httpClient, debugConfig.TestSubmissionID, submissionDetailsQuery, session, csrfToken)
		if err != nil {
			log.Fatalf("Failed to fetch submission details: %v", err)
		}
		log.Printf("Code for submission %s:\n%s", debugConfig.TestSubmissionID, code)
		return true
	}

	// If testSlug is provided, fetch submissions for that problem
	//if debugConfig.TestSlug != "" {
	//	count := fetchSubmissionsForProblem(httpClient, debugConfig.TestSlug, debugConfig.TestSlug, session, csrfToken, submissionListQuery, submissionDetailsQuery)
	//	log.Printf("Processed %d submissions for %s", count, debugConfig.TestSlug)
	//	return true
	//}
	return false
}

func main() {
	// Load queries
	debugConfig, err := utils.LoadDebugConfig()
	if err != nil {
		log.Fatalf("Failed to load debug config: %v", err)
	}
	utils.InitDebugLogger(debugConfig)
	if err := loadQueries(); err != nil {
		log.Fatalf("Failed to load queries: %v", err)
	}

	// Parse cookies
	session, csrfToken, err := utils.ParseCookies(cookieFile)
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

	// Handle debug mode if enabled
	if handleDebugMode(debugConfig, httpClient, session, csrfToken) {
		return
	}

	// Fetch and process problems
	if err := processSolvedProblems(httpClient, session, csrfToken); err != nil {
		log.Fatalf("Failed to process problems: %v", err)
	}
}

func processSolvedProblems(httpClient *http.Client, session, csrfToken string) error {
	// Fetch problems
	problems, err := fetchSolvedProblemList(httpClient, session, csrfToken, maxProblems)
	if err != nil {
		return fmt.Errorf("failed to fetch submissions: %w", err)
	}
	if problems == nil || len(problems) == 0 {
		log.Printf("No solved problems found for the user")
		return nil
	}

	totalProcessed := processProblems(httpClient, problems, session, csrfToken)
	log.Printf("Completed processing %d problems with %d total submissions", len(problems), totalProcessed)
	return nil
}

func processProblems(httpClient *http.Client, problems []utils.Problem, session, csrfToken string) int {
	totalProcessed := 0
	for _, problem := range problems {
		// Create directory for the problem
		dir := getQuestionDirectory(problem.ID, problem.Title)
		if err := os.MkdirAll(dir, 0755); err != nil {
			log.Printf("Failed to create directory for problem %s: %v", problem.Title, err)
			continue
		}

		log.Printf("Processing problem: %s (%s)", problem.Title, problem.TitleSlug)
		count := fetchSubmissionsForProblem(httpClient, problem, session, csrfToken)
		totalProcessed += count
	}
	return totalProcessed
}

func loadQueries() error {
	var err error
	problemsetQuery, submissionListQuery, submissionDetailsQuery, _, err = utils.ParseQueries(queryFile)
	if err != nil {
		return fmt.Errorf("failed to load queries: %w", err)
	}
	return nil
}

func fetchSubmissionListForProblem(httpClient *http.Client, session, csrfToken, titleSlug string) ([]Submission, error) {
	var submissions []Submission
	limit := limitPerRequest
	offset := 0

	for {
		request := &utils.GraphQLRequest{
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

		response, err := utils.MakeGraphQLRequest[Response](httpClient, session, csrfToken, request)
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

func fetchSolvedProblemList(httpClient *http.Client, session, csrfToken string, maxCount int) ([]utils.Problem, error) {
	var problems []utils.Problem
	limit := limitPerRequest
	skip := 0

	for {
		request := &utils.GraphQLRequest{
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
					Questions  []utils.Problem `json:"questions"`
					TotalCount int             `json:"totalCount"`
					HasMore    bool            `json:"hasMore"`
				} `json:"problemsetQuestionListV2"`
			} `json:"data"`
		}

		response, err := utils.MakeGraphQLRequest[Response](httpClient, session, csrfToken, request)
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

func fetchSubmissionsForProblem(httpClient *http.Client, problem utils.Problem, session, csrfToken string) int {
	// Get the list of submissions
	submissions, err := fetchSubmissionListForProblem(httpClient, session, csrfToken, problem.TitleSlug)
	if err != nil {
		log.Printf("Failed to fetch submission list for %s: %v", problem.TitleSlug, err)
		return 0
	}
	return processSubmissions(httpClient, submissions, problem, session, csrfToken)
}

func processSubmissions(httpClient *http.Client, submissions []Submission, problem utils.Problem, session, csrfToken string) int {
	total := len(submissions)
	totalSubmissions := 0

	bar := progressbar.NewOptions(total,
		progressbar.OptionSetDescription(fmt.Sprintf("Fetching submissions for %s", problem.Title)),
		progressbar.OptionSetItsString("submission"),
		progressbar.OptionShowCount(),
		progressbar.OptionShowIts())

	for i, submission := range submissions {
		if processSubmission(httpClient, submission, problem, i, session, csrfToken) {
			totalSubmissions++
		}
		bar.Add(1)
		time.Sleep(500 * time.Millisecond)
	}

	bar.Finish()
	log.Printf("Total submissions fetched and saved for %s: %d", problem.Title, totalSubmissions)
	return totalSubmissions
}

func processSubmission(httpClient *http.Client, submission Submission, problem utils.Problem, index int, session, csrfToken string) bool {
	code, err := getSubmissionDetails(httpClient, submission.ID, submissionDetailsQuery, session, csrfToken)
	if err != nil {
		log.Printf("Failed to fetch details for submission %s: %v", submission.ID, err)
		code = "// Code not available"
	}

	if err := saveSubmission(submission, code, index, problem); err != nil {
		log.Printf("Failed to save submission %s: %v", submission.ID, err)
		return false
	}
	return true
}
func getSubmissionDetails(httpClient *http.Client, submissionID, query, session, csrfToken string) (string, error) {
	request := &utils.GraphQLRequest{
		Query: query,
		Variables: map[string]interface{}{
			"submissionId": submissionID,
		},
		OperationName: "submissionDetails",
	}

	response, err := utils.MakeGraphQLRequest[SubmissionDetailsResponse](httpClient, session, csrfToken, request)
	if err != nil {
		return "", fmt.Errorf("failed to fetch submission details: %w", err)
	}

	return response.Data.SubmissionDetails.Code, nil
}
func getQuestionDirectory(id int, title string) string {
	// Format: "0001.Title"
	dirName := fmt.Sprintf("%04d. %s", id, title)
	return filepath.Join("solutions", dirName)
}

func saveSubmission(submission Submission, code string, index int, problem utils.Problem) error {
	// Get the target directory using problem ID
	dir := getQuestionDirectory(problem.ID, problem.Title)

	// Determine file extension
	lang := strings.ToLower(submission.LangName)
	ext := langExt[lang]
	if ext == "" {
		ext = "txt"
	}

	// Create file path with index
	filename := fmt.Sprintf("%03d.%s", index, ext)
	filePath := filepath.Join(dir, filename)

	// Create and write to file
	file, err := os.Create(filePath)
	if err != nil {
		return fmt.Errorf("failed to create file %s: %w", filePath, err)
	}
	defer file.Close()

	if _, err := io.WriteString(file, code); err != nil {
		return fmt.Errorf("failed to write to file %s: %w", filePath, err)
	}

	log.Printf("Saved: %s", filePath)
	return nil
}
