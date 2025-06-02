package utils

import (
	"fmt"
	"os"
	"strings"
)

func ParseCookies(cookieFile string) (string, string, error) {
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
