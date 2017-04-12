package main

import (
	"net"
	"time"
)

// START TYPEDEF OMIT
type Server struct {
	listener       net.Listener
	ConnectTimeout time.Duration
	ReadTimeout    time.Duration
	MaxConnections time.Duration
}

// END TYPEDEF OMIT

func (s *Server) Addr() net.Addr {
	return s.listener.Addr()
}

func (s *Server) Shutdown() error {
	return s.listener.Close()
}

// START OPTIONS OMIT
func NewServer(addr string, options ...func(*Server)) (*Server, error) {
	l, err := net.Listen("tcp", addr)
	if err != nil {
		return nil, err
	}

	s := Server{listener: l}
	for _, option := range options {
		option(&s)
	}

	return &s, nil
}

// END OPTIONS OMIT
