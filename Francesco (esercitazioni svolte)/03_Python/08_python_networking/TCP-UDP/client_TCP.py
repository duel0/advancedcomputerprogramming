import sys, socket

HOST = '127.0.0.1'
PORT = 50209
BUF = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.connect((HOST, PORT))
s.send("Hello, world".encode("utf-8"))
data = s.recv(BUF).decode("utf-8")

print(data)