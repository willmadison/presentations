package main

import (
	"encoding/json"
	"fmt"
	"io"
	"io/ioutil"
	"net"
	"net/http"
	"net/url"
	"os"
	"strings"
	"time"

	"golang.org/x/net/context"

	"google.golang.org/grpc"

	"github.com/lstoll/wsnet"
	pb "github.com/willmadison/yodecho/protos/echoes"
)

type server struct{}

// START YODECHO OMIT
func (s *server) Echo(ctx context.Context, request *pb.EchoRequest) (*pb.EchoResponse, error) { // HL
	fmt.Println("translating", request.Content, "to Yodish...")

	select {
	case <-ctx.Done(): // HL
		return nil, ctx.Err()
	default:
		yodish, err := translateToYodish(request.Content) // HL
		// Error handling...
		if err != nil { // OMIT
			fmt.Println("encountered an unexpected error translating", request.Content, "to Yodish:", err) // OMIT
		} // OMIT

		return &pb.EchoResponse{Response: yodish}, err // HL
	}
}

// END YODECHO OMIT

func (s *server) ChattyEcho(stream pb.Echo_ChattyEchoServer) error {
	for {
		request, err := stream.Recv()

		switch {
		case err == io.EOF:
			return nil
		case err != nil:
			return err
		default:
			yodish, err := translateToYodish(request.Content)

			if err != nil {
				fmt.Println("encountered an unexpected error translating", request.Content, "to Yodish:", err)
			}

			response := &pb.EchoResponse{Response: yodish}

			if err := stream.Send(response); err != nil {
				return err
			}
		}
	}
}

type yodishResponse struct {
	Contents struct {
		Translated  string `json:"translated"`
		Text        string `json:"text"`
		Translation string `json:"translation"`
	} `json:"contents"`
}

func translateToYodish(text string) (string, error) {
	apiKey := os.Getenv("API_KEY")
	endpoint := os.Getenv("YODISH_API_ENDPOINT")

	client := http.Client{}
	values := url.Values{}
	values.Add("text", text)

	request, err := http.NewRequest(http.MethodPost, endpoint, strings.NewReader(values.Encode()))
	if err != nil {
		return "", err
	}

	request.Header.Add("X-Funtranslations-Api-Secret", apiKey)
	request.Header.Add("Content-Type", "application/x-www-form-urlencoded")

	response, err := client.Do(request)
	if err != nil {
		return "", err
	}

	defer response.Body.Close()

	contents, err := ioutil.ReadAll(response.Body)
	if err != nil {
		return "", err
	}

	fmt.Println("rawResponse:", string(contents))

	var translated yodishResponse

	err = json.Unmarshal(contents, &translated)
	if err != nil {
		return "", err
	}

	return translated.Contents.Translated, nil
}

func main() {
	//START SERVE OMIT
	rpcPort := net.JoinHostPort("", os.Getenv("PORT"))                  // HL
	listener, err := wsnet.ListenWithKeepalive(rpcPort, 10*time.Second) // HL

	if err != nil {
		fmt.Print("encountered some sort of error:", err)
	}

	fmt.Println("serving", rpcPort+".....")

	s := grpc.NewServer()
	pb.RegisterEchoServer(s, &server{})
	s.Serve(listener)

	//END SERVE OMIT
}
