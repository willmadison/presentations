package main

import (
	"fmt"
	"net"
	"net/http"
)

func main() {
	http.HandleFunc("/", func(response http.ResponseWriter, request *http.Request) { // HL
		queryParameters := request.URL.Query()

		var name string
		if name = queryParameters.Get("name"); name == "" {
			name = "Mr./Ms. Anonymous"
		}
		fmt.Fprintf(response, "Hello %s!", name) // HL
	})

	port := "9100"
	fmt.Println("Now greeting Gophers on port", port, "...")
	http.ListenAndServe(net.JoinHostPort("", port), nil)
}
