package main

import "fmt"

type Vertex struct {
	Lat, Long float64
}

var m = map[string]Vertex{
	"Pivotal Labs (San Francisco)": Vertex{
		37.7818, -122.4040,
	},
	"The Varsity": Vertex{
		33.7716, -84.3893,
	},
}

func main() {
	fmt.Println(m)
}
