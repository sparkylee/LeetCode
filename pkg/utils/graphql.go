package utils

import (
	"bytes"
	"encoding/json"
	"fmt"
	"github.com/vektah/gqlparser/v2/ast"
	"github.com/vektah/gqlparser/v2/parser"
	"gopkg.in/yaml.v3"
	"io"
	"log"
	"net/http"
	"os"
	"strings"
)

type GraphQLRequest struct {
	Query         string                 `json:"query"`
	Variables     map[string]interface{} `json:"variables"`
	OperationName string                 `json:"operationName"`
}
type DebugConfig struct {
	TestSlug         string `yaml:"testSlug"`
	TestSubmissionID string `yaml:"testSubmissionId"`
	Debug            bool   `yaml:"debug"`
}

var debugLog = log.New(io.Discard, "[DEBUG] ", log.LstdFlags)

func InitDebugLogger(config *DebugConfig) {
	if config != nil && config.Debug {
		debugLog = log.New(os.Stdout, "[DEBUG] ", log.LstdFlags)
	} else {
		debugLog = log.New(io.Discard, "[DEBUG] ", log.LstdFlags)
	}
}
func LoadDebugConfig() (*DebugConfig, error) {
	data, err := os.ReadFile("debug.yaml")
	if err != nil {
		if os.IsNotExist(err) {
			return &DebugConfig{}, nil // Return empty config if file doesn't exist
		}
		return nil, fmt.Errorf("failed to read debug config: %w", err)
	}

	var config DebugConfig
	if err := yaml.Unmarshal(data, &config); err != nil {
		return nil, fmt.Errorf("failed to parse debug config: %w", err)
	}

	return &config, nil
}
func MakeGraphQLRequest[T any](httpClient *http.Client, session, csrfToken string, request *GraphQLRequest) (*T, error) {
	bodyBytes, err := json.Marshal(request)
	if err != nil {
		return nil, fmt.Errorf("failed to marshal request body: %v", err)
	}
	debugLog.Printf("Request body: %s", string(bodyBytes))

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
	debugLog.Printf("Response body: %s", string(bodyBytes))

	// Create a new reader with the bytes for decoding the JSON
	resp.Body = io.NopCloser(bytes.NewBuffer(bodyBytes))

	var response T
	if err := json.NewDecoder(resp.Body).Decode(&response); err != nil {
		return nil, fmt.Errorf("failed to decode response: %v", err)
	}

	return &response, nil
}

// ParseQueries reads a GraphQL query file and extracts specific queries by name.
func ParseQueries(queryFile string) (string, string, string, error) {
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
