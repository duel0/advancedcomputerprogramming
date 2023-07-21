import sys
from interface import Service
import multiprocess as mp
import socket

def proc_fun(conn, obj):
    data = conn.recv(1024)

    if "preleva" in str(data.decode()):
        mess = obj.preleva()
    else:
        val = (data.decode()).split("-")[1]
        mess = obj.deposita(val)
    
    conn.send((mess+"\n").encode())
    conn.close()

class ServiceProxy(Service):
    def __init__(self, port, queue):
        self.port=port
        self.queue=queue
    def deposita(self, message):
        pass
    def preleva(self):
        pass
    def run_skeleton(self):
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(('localhost',self.port))
        s.listen(5)
        print("La socket ascolta...")
        while True:
            conn, addr = s.accept()
            p = mp.Process(target=proc_fun, args=(conn, self))
            p.start()

class ServiceImpl(ServiceProxy):
    def deposita(self, message):
        self.queue.put(message)
        return "Depositato"
    def preleva(self):
        data = self.queue.get()
        return data
    
if __name__=="__main__":
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Dammi il porto!")
    q = mp.Queue(5)
    s = ServiceImpl(int(PORT),q)
    s.run_skeleton()