import socket

msgServer = "Ciao client!"
serverAddressPort = ("localhost".encode("utf-8"),0)
bufferSize = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(serverAddressPort)

cur_port = s.getsockname()[1]
print("Server listening on port: ",cur_port)
msgClient, addr = s.recvfrom(bufferSize)

print("Messaggio: "+msgClient.decode("utf-8"))
print("Client: {}".format(addr))

print("[SERVER] Invio al client...")
s.sendto(msgServer.encode("utf-8"),addr)

s.close()