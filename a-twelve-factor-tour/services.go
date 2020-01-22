package main

import (
	"os"
)

// START EXAMPLE OMIT
func main() {
	postgresConnString := os.Getenv("POSTGRES_CONNECTION_INFO")
	apiKey := os.Getenv("TWITTER_API_KEY")
	apiSecret := os.Getenv("TWITTER_API_SECRET")
	os.Exit(run(newPostgresStore(postgressConnString), newTwitterFeed(apiKey, apiSecret))
}
// END EXAMPLE OMIT

type item struct {
	name, contents string
}

type dataStore interface {
	save(item) error
}

type socialFeed interface {
	fetchTimeline() []item
}

func run(store dataStore, feed socialFeed) int {
	return 0
}
