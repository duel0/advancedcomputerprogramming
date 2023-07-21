import multiprocess as mp
import socket
import time
import stomp

def proc_fun(msg, obj):
    data = msg.decode()
    numero = data.split("-")[1]
    print("Ricevuto il numero: ",numero)
    obj.produci(numero)
    time.sleep(2)
    newNumero = obj.consuma()
    print("Invio il numero: ",newNumero)
    conn = stomp.Connection([('127.0.0.1',61613)], auto_content_length=False)
    conn.connect(wait=True)
    conn.send("/queue/prodcons", str(newNumero))

class ServiceSkeleton():
    def __init__(self, spaziodisp, msgdisp, queue) -> None:
        
        self.spaziodisp=spaziodisp
        self.msgdisp=msgdisp
        self.queue=queue
        self.num=0
    def run_skeleton(self):
        print("Runno lo skeleton!")
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.bind(("127.0.0.1",0))
        print("Ricevo porto: ",s.getsockname()[1])
        while True:
            msg, addr = s.recvfrom(1024)
            p = mp.Process(target=proc_fun, args=(msg,self))
            p.start()

class Service(ServiceSkeleton):
    def produci(self, numero):
        with self.spaziodisp:
            while(self.num==5):
                self.spaziodisp.wait()
            self.queue.append(numero)
            self.num=self.num+1
            self.msgdisp.notify()
    def consuma(self):
        with self.msgdisp:
            while(self.num==0):
                self.msgdisp.wait()
            val = self.queue.pop(0)
            self.num=self.num-1
            self.spaziodisp.notify()
        return val

if __name__=="__main__":
    queue = []
    lock = mp.Lock()
    spaziodisp = mp.Condition(lock=lock)
    msgdisp = mp.Condition(lock=lock)
    #mutex = mp.Semaphore(1)
    #spaziodisp = mp.Semaphore(5)
    #msgdisp = mp.Semaphore(0)
    service = Service(spaziodisp, msgdisp, queue)
    service.run_skeleton()