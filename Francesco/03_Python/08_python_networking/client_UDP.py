import socket, sys

def client(port):
    mscClient = "Ciao server!"
    serverAddressPort = ("localhost".encode("utf-8"), port)
    bufferSize = 1024

    s=socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    print("[CLIENT] Invio dati: "+mscClient)
    s.sendto(mscClient.encode("utf-8"), serverAddressPort)

    msgServer, addr = s.recvfrom(bufferSize)
    print("Risposta server: "+msgServer.decode("utf-8"))
    s.close()

if __name__=="__main__":
    try:
        port = sys.argv[1]
    except IndexError:
        print("Please, specify PORT arg...")

    assert port != "", 'specify port'
    client(int(port))