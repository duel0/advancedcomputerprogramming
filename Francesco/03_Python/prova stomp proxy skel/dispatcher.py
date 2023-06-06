import sys, stomp, time, multiprocess, socket
from multiprocess import Process
from interface import Service

def proc_fun(conn, proxy, mess):

    request = mess.split('-')[0]

    if 'deposita' in request:

        id = mess.split('-')[1]
        result = proxy.deposita()
    else:
        result = proxy.preleva()
    conn.send('/queue/response', result)

class ServiceProxy(Service): # il proxy si preoccupa della comunicazione
    def __init__(self, port):
        self.port=port
        self.ip='localhost'
        self.buffer_size=1024


    def preleva(self):
        # creare la socket tcp, inviare la richiesta di preleva, aspettare la risposta e chiudere la socket
        s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((self.ip, self.port))

        message = "preleva"

        s.send(message.encode("utf-8")) # manda sempre stringhe sulle socket

        data = s.recv(self.buffer_size)
        s.close()
        return data

    def deposita(self, mess):
        s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((self.ip, self.port))

        message = "deposita-"+str(mess)

        s.send(message.encode("utf-8")) # manda sempre stringhe sulle socket

        data = s.recv(self.buffer_size)
        s.close()
        return data

class MyListener(stomp.ConnectionListener):

    def __init__(self, conn, port):
        self.conn=conn
        self.port=port

    def on_message(self, frame):
        print("[DISPATCHER] Richiesta ricevuta: %s" % frame.body)

        proxy = ServiceProxy(int(self.port))

        p = Process(target=proc_fun, args=(conn, proxy, frame.body))

        p.start()



if __name__=="__main__":
    try:
        PORT = sys.argv[1]

    except IndexError:
        print("please, add port arg")

    conn = stomp.Connection([('localhost',61613)])

    conn.set_listener('',MyListener(conn,PORT))

    conn.connect(wait=True)

    conn.subscribe(destination='/queue/response', id=1, ack='auto')

    print("[DISPATCHER] wait for requests...")

    while True:
        time.sleep(60)