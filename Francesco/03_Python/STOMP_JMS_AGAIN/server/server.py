import sys, socket
import multiprocess as mp
from interface import Service
def proc_fun(conn, object):
    data = (conn.recv(1024)).decode()
    if "preleva" in data:
        msg = object.preleva()
    elif "deposita" in data:
        val = str(data.split('-')[1])
        msg = object.deposita(val)
    conn.send((msg+"\n").encode())
    conn.close()

class ServiceSkeleton(Service):
    def __init__(self, queue) -> None:
        self.port=2500
        self.queue=queue
    def deposita(self, val):
        pass
    def preleva(self):
        pass
    def run_skeleton(self):
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(('127.0.0.1',self.port))
        s.listen(5)
        while True:
            conn, addr = s.accept()
            p = mp.Process(target=proc_fun, args=(conn,self))
            p.start()

class ServiceImpl(ServiceSkeleton):
    def deposita(self, val):
        self.queue.put(val)
        s = "Depositato: "+str(val)
        return s
    def preleva(self):
        val = self.queue.get()
        s= "Prelevato: "+str(val)
        return s
    
if __name__=="__main__":
    queue = mp.Queue()
    service = ServiceImpl(queue)
    service.run_skeleton()