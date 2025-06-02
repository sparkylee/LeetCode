package main

import (
	"flag"
	"leetcode/pkg/utils"
	"log"
	"net/http"
	"os"
	"time"
)

var (
	maxProblems = 10000
	cookieFile  string
)

func init() {
	flag.StringVar(&cookieFile, "cookies", "leetcode.com_cookies.txt", "Path to the cookies file")
}

func main() {
	flag.Parse()
	debugConfig, err := utils.LoadDebugConfig()
	if err != nil {
		log.Fatalf("Failed to load debug config: %v", err)
	}
	// Parse cookies from file
	session, csrfToken, err := utils.ParseCookies(cookieFile)
	if err != nil {
		log.Fatalf("Failed to parse cookies: %v", err)
	}

	// Create HTTP client
	httpClient := &http.Client{
		Timeout: 30 * time.Second,
	}

	// Create solutions directory if it doesn't exist
	if err := os.MkdirAll("solutions", 0755); err != nil {
		log.Fatalf("Failed to create solutions directory: %v", err)
	}

	// Fetch favorite problems
	problems, err := utils.FetchFavoriteList(httpClient, session, csrfToken, debugConfig.FavoriteSlug, maxProblems)
	if err != nil {
		log.Fatalf("Failed to fetch favorite problems: %v", err)
	}

	if len(problems) == 0 {
		log.Println("No problems found in favorite list")
		return
	}

	log.Printf("Found %d problems in favorite list", len(problems))

	// Process each problem
	for _, problem := range problems {
		log.Printf("Processing: %s (%s)", problem.Title, problem.TitleSlug)
	}
	log.Printf("Completed processing %d problems from favorite list", len(problems))
}
