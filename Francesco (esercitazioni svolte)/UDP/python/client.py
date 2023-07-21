import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
msg = "Ciao serverone pisciazz"
s.sendto(msg.encode(),('127.0.0.1',60010))

msg, addr = s.recvfrom(1024)
print("Ricevo: ",msg.decode())
s.close()