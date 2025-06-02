package utils

import (
	"bytes"
	"encoding/json"
	"fmt"
	"gopkg.in/yaml.v3"
	"io"
	"log"
	"net/http"
	"os"
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
