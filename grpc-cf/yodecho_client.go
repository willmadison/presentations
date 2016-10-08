package main

import (
	"bufio"
	"context"
	"fmt"
	"os"

	"github.com/lstoll/wsnet"
	pb "github.com/willmadison/yodecho/protos/echoes"

	"google.golang.org/grpc"
)

func main() {
	//START DIAL OMIT

	address := os.Getenv("ECHO_SERVER_ADDRESS")
	conn, err := grpc.Dial(address, grpc.WithInsecure(), grpc.WithDialer(wsnet.Dial)) // HL

	//END DIAL OMIT
	if err != nil {
		panic(err)
	}

	defer conn.Close()

	c := pb.NewEchoClient(conn)

	scanner := bufio.NewScanner(os.Stdin)
	fmt.Print("> ")

	for scanner.Scan() {
		command := scanner.Text()

		switch command {
		case "exit":
			return
		default:
			//START ECHO OMIT
			request := &pb.EchoRequest{
				Content: command,
			}

			response, err := c.Echo(context.Background(), request) // HL
			if err != nil {
				fmt.Println("encountered an unexpected error:", err)
				continue
			}

			fmt.Println("Yoda says:", response)
			//END ECHO OMIT
		}
		fmt.Print("> ")
	}
}

//END OMIT
