import socket
import sys

IP='0.0.0.0'
PORT=0
BUFFER_SIZE=1024

s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.bind((IP,PORT))

try:
    s.listen(1)
    print("sockname=> ",s.getsockname())
    cur_port = s.getsockname()[1]

    print("Server on: ",IP,"port: ",cur_port)

    conn, addr = s.accept()
    print('Connection address: {}'.format(addr))

except KeyboardInterrupt:
    print("Hai premuto control c")
    s.close()
    sys.exit()

toClient="The world never says hello back\n"
data = conn.recv(BUFFER_SIZE)
print("Received data: "+data.decode("utf-8"))
conn.send(toClient.encode("utf-8"))

conn.close()
s.close()