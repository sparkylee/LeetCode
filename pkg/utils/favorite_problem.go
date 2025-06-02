package utils

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
	"os"
	"time"
)

type FavoriteQuestion struct {
	Questions   []Problem `json:"questions"`
	TotalLength int       `json:"totalLength"`
	HasMore     bool      `json:"hasMore"`
}

type FavoriteResponse struct {
	Data struct {
		FavoriteQuestionList FavoriteQuestion `json:"favoriteQuestionList"`
	} `json:"data"`
}

var favoriteListQuery string

func loadQueries() error {
	var err error
	data, err := os.ReadFile("query.graphql")
	if err != nil {
		return fmt.Errorf("query.graphql not found: %w", err)
	}
	// Parse the queries from the file
	favoriteListQuery, err = ExtractQuery(string(data), "favoriteQuestionList")
	if err != nil {
		return fmt.Errorf("failed to extract favorite list query: %w", err)
	}

	return nil
}

func EnsureQueriesLoaded() error {
	if favoriteListQuery == "" {
		return loadQueries()
	}
	return nil
}
func FetchFavoriteList(httpClient *http.Client, session, csrfToken, favoriteSlug string, maxCount int) ([]Problem, error) {
	if err := EnsureQueriesLoaded(); err != nil {
		return nil, fmt.Errorf("failed to load queries: %w", err)
	}
	var allProblems []Problem
	skip := 0
	limit := 100

	for {
		variables := map[string]interface{}{
			"skip":         skip,
			"limit":        limit,
			"favoriteSlug": favoriteSlug,
			"filtersV2": map[string]interface{}{
				"filterCombineType": "ALL",
				"statusFilter": map[string]interface{}{
					"questionStatuses": []string{},
					"operator":         "IS",
				},
			},
			"searchKeyword": "",
			"sortBy": map[string]interface{}{
				"sortField": "CUSTOM",
				"sortOrder": "ASCENDING",
			},
		}

		payload := GraphQLRequest{
			OperationName: "favoriteQuestionList",
			Query:         favoriteListQuery,
			Variables:     variables,
		}

		jsonData, err := json.Marshal(payload)
		if err != nil {
			return nil, fmt.Errorf("failed to marshal request: %w", err)
		}

		req, err := http.NewRequest("POST", graphqlEndpoint, bytes.NewBuffer(jsonData))
		if err != nil {
			return nil, fmt.Errorf("failed to create request: %w", err)
		}

		setupHeaders(req, session, csrfToken)

		resp, err := httpClient.Do(req)
		if err != nil {
			return nil, fmt.Errorf("failed to send request: %w", err)
		}
		defer resp.Body.Close()

		var response FavoriteResponse
		if err := json.NewDecoder(resp.Body).Decode(&response); err != nil {
			return nil, fmt.Errorf("failed to decode response: %w", err)
		}

		allProblems = append(allProblems, response.Data.FavoriteQuestionList.Questions...)

		// Break if no more problems or reached maxCount
		if !response.Data.FavoriteQuestionList.HasMore ||
			(maxCount > 0 && len(allProblems) >= maxCount) {
			break
		}

		skip += limit
		time.Sleep(500 * time.Millisecond) // Rate limiting
	}

	// Trim to maxCount if needed
	if maxCount > 0 && len(allProblems) > maxCount {
		allProblems = allProblems[:maxCount]
	}

	return allProblems, nil
}
