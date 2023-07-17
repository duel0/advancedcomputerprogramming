import socket, sys

HOST = '0.0.0.0'
PORT = 0
BUF = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.bind((HOST,PORT))

try:
    s.listen(1)
    print(s.getsockname())
    cur_port = (s.getsockname()[1])
    print(cur_port)
    conn, addr = s.accept()
except KeyboardInterrupt:
    print("Hai interrotto!")
    s.close()

data = conn.recv(BUF)
print("Messaggio: ", data.decode("utf-8"))

returnMess = "Ciaoooo"
conn.send(returnMess.encode("utf-8"))