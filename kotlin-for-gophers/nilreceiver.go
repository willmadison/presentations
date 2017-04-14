package main

import "fmt"

type node struct {
	value       int
	left, right *node
}

func (n *node) Sum() int {
	if n == nil {
		return 0
	}

	return n.value + n.left.Sum() + n.right.Sum()
}

func main() {
	tree := &node{value: 1, right: &node{value: 9}}
	fmt.Println("Sum =", tree.Sum())
}
