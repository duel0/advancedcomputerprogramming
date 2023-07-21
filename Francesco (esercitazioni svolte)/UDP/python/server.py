import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(('127.0.0.1',0))
port = s.getsockname()[1]
print("Sono stato bindato alla porta ",port)
msg, addr = s.recvfrom(1024)
print("Ho ricevuto: ",msg.decode())
resp = "Ricchionissimo!!!"
s.sendto(resp.encode(),addr)
s.close()