package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	maxRetries := 3
	var multiTenant bool

	if  rawRetries := os.Getenv("MAX_RETRIES"); len(rawRetries) > 0 {
		maxRetries, _ = strconv.Atoi(rawRetries)
	}

	if  rawMultiTenant := os.Getenv("MULTI_TENANT"); len(rawMultiTenant) > 0 {
		multiTenant, _ = strconv.ParseBool(rawMultiTenant)
	}

	fmt.Println("Max Retries =", maxRetries)
	fmt.Println("Multi-Tenant Mode =", multiTenant)
}