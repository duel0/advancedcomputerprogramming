from interface import Subject
import socket, sys
import threading

def thd_fun(c, self):
    data = c.recv(1024)

    result = self.request(data)

    c.send(result)

    c.close()

class Skeleton(Subject):
    def __init__(self, port):
        self.port=port
    def request(self, data):
        pass
    def run_skeleton(self):
        host = 'localhost'
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((host,self.port))
        self.port = s.getsockname()[1]

        print("Socket binded to port",self.port)
        s.listen(5)
        print("Socket is listening...")
        while True:
            c, addr = s.accept()
            print("Connected to: ",addr[0],":",addr[1])
            t=threading.Thread(target=thd_fun,args=(c,self),)
            print("Starting handler...")
            t.start()
        s.close()

class RealSubject(Skeleton):
    def request(self, data):
        data = data[::-1]
        return data

if __name__=="__main__":
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Specifica porta")
    print("Server running")
    realSubject = RealSubject(int(PORT))
    realSubject.run_skeleton()