package main

import (
	"context"
	"fmt"
	"net/http"
	"os"
	"os/signal"
	"time"
)

func main() {
	router := http.NewServeMux()

	router.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(http.StatusOK)
	})

	server := &http.Server{
		Addr:         ":8888",
		Handler:      router,
		ReadTimeout:  5 * time.Second,
		WriteTimeout: 15 * time.Second,
		IdleTimeout:  30 * time.Second,
	}

	// START SETUP OMIT
	done := make(chan struct{})
	quit := make(chan os.Signal)

	signal.Notify(quit, os.Interrupt)

	go shutdownGracefully(server, quit, done)

	// END SETUP OMIT

	fmt.Println("handling requests...")
	if err := server.ListenAndServe(); err != nil && err != http.ErrServerClosed {
		fmt.Println("unable to successfully start...")
		os.Exit(1)
	}

	<-done
	fmt.Println("shutdown complete...")
}

// START GRACEFULLY OMIT

func shutdownGracefully(server *http.Server, quit chan os.Signal, done chan struct{}) {
	<-quit

	fmt.Println("shutting down server...")

	ctx, cancel := context.WithTimeout(context.Background(), 8*time.Second)
	defer cancel()

	server.SetKeepAlivesEnabled(false)
	if err := server.Shutdown(ctx); err != nil {
		fmt.Println("unable to gracefully shutdown:", err)
	}

	close(done)
}

// END GRACEFULLY OMIT
