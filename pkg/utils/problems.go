package utils

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
