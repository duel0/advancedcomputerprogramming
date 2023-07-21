import socket, sys, threading
from interface import Subject
def thd_fun(conn, self):
    data = conn.recv(1024)

    newdata = self.request(data)

    conn.send(newdata)
    conn.close()

class MySubject(Subject):
    def __init__(self, port):
        self.port=port
    def request(self, data):
        pass
    def runSkeleton(self):
        localhost='127.0.0.1'
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((localhost,self.port))
        cur_port = s.getsockname()[1]
        print("Binded to port: ",cur_port)
        s.listen(5)
        conn, addr = s.accept()
        print("Accetto connessione da: ",addr[0],":",addr[1])
        t = threading.Thread(target=thd_fun, args=(conn, self))
        t.start()

class RealSubject(MySubject):
    def request(self, data):
        new = data[::-1]
        return new
    
try:
    PORT = sys.argv[1]
except IndexError:
    print("Aggiungi porto!")

a = RealSubject(int(PORT))
a.runSkeleton()