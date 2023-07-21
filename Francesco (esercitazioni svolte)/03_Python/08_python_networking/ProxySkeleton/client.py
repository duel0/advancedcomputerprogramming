import socket, sys

def main(PORT):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(('127.0.0.1',PORT))
    s.send("Ciao Mondo".encode("utf-8"))
    data = s.recv(1024).decode("utf-8")
    print("Ho ricevuto indietro: ",data)

if __name__=="__main__":
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Dammi il porto!")

    main(int(PORT))