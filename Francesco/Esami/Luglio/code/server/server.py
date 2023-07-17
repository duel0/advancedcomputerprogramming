from interface import ILogging
import socket, sys
import multiprocess as mp
import stomp
import time

def proc_fun(conn, obj):
    string, int = ((conn.recv(1024)).decode()).split("#")
    print("[SERVER] Ho ricevuto ",string," - ",int)
    obj.log(string,int)

def consumer_fun(obj):
    conn = stomp.Connection([('127.0.0.1',61613)],auto_content_length=False)
    conn.connect(wait=True)
    while True:
        time.sleep(4)
        message = obj.queue.get()
        if "2" in message:
            conn.send("/queue/error",message)
        else:
            conn.send("/queue/info",message)
    conn.disconnect()

class LoggingSkeleton(ILogging):
    def __init__(self, port, queue):
        self.port=port
        self.queue=queue
    def log(self, string, int):
        pass
    def run_skeleton(self):
        s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(('127.0.0.1',self.port))
        s.listen(5)
        while True:
            conn, addr = s.accept()
            p = mp.Process(target=proc_fun, args=(conn,self))
            p.start()
class LoggingImpl(LoggingSkeleton):
    def log(self, string, int):
        newdata = string+"-"+int
        self.queue.put(newdata)
        print("[SERVER] Ho inserito ",newdata," nella coda!")

if __name__=="__main__":
    try:
        PORT=sys.argv[1]
    except IndexError:
        print("Dammi il porto!")
        sys.exit()
    queue = mp.Queue(5)
    logger = LoggingImpl(int(PORT),queue)
    t = mp.Process(target=consumer_fun, args=(logger,))
    t.start()
    logger.run_skeleton()