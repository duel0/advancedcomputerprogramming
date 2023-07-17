from interface import ILogging
import socket, sys, random, time

class Logging(ILogging):
    def __init__(self, host, port):
        self.host=host
        self.port=port
    def log(self, string, int):
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((self.host, self.port))
        messaggio = string+"#"+int
        s.send(messaggio.encode())
        s.close()

if __name__ == "__main__":
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Dammi il porto!")
        sys.exit()
    logger = Logging('127.0.0.1',int(PORT))
    messaggi = ["success","checking","fatal","exception"]

    for i in range(10):
        time.sleep(1)
        tipo = random.randint(0,3)
        if tipo<2:
            logger.log(messaggi[tipo],str(tipo))
        else:
            logger.log(messaggi[tipo],str(2))
    