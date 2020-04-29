package handlers

import (
	"net/http"
	"sync"
)

var once sync.Once

func handleRequest(w http.ResponseWriter, r *http.Request) {
	once.Do(func() {
		expensiveHeavyOperation()
	})

	// Work happens here using the expensive resource...
}
