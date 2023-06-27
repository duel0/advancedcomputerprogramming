import socket, sys, stomp, time
from multiprocess import Process
from interface import Service

# process function
def proc_fun(conn, proxy, mess):

    request = mess.split('-')[0]

    if request == "deposita" :
        id = mess.split('-')[1]
        result = proxy.deposita(id)
    else:
        result = proxy.preleva()
    
    conn.send('/queue/response', result)

# Proxy
class ServiceProxy(Service):
    
    def __init__(self, port):
        self.port = port
        self.ip = 'localhost'
        self.buffer_size = 1024

    def preleva(self):

        # Create the socket and connect
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((self.ip, self.port))

        # Generate and send the request
        message = "preleva"
        s.send(message.encode("utf-8"))

        # Get the response
        data = s.recv(self.buffer_size)

        s.close()

        return data
    
    def deposita(self, id):

        # Create the socket and connect
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((self.ip, self.port))

        # Generate and send the request
        message = "deposita-" + str(id)
        s.send(message.encode("utf-8"))

        data = s.recv(self.buffer_size)

        # Get the response
        s.close()

        return data


# Listener
class MyListener(stomp.ConnectionListener):
    
    def __init__(self, conn, port):
        self.port = port
        self.conn = conn

    def on_message(self, frame):
        
        print('[DISPATCHER] Request received: "%s"' % frame.body)

        # Generate the Proxy
        proxy = ServiceProxy(int(self.port))

        # Start a process to serve the request
        p = Process(target=proc_fun, args=(conn, proxy, frame.body))
        p.start()


if __name__ == "__main__":

    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Please, specify PORT arg")

    # Create connection
    conn = stomp.Connection([('127.0.0.1', 61613)])

    # Set the listener
    conn.set_listener('', MyListener(conn, PORT))

    # Connect and subscribe to the queue 'request'
    conn.connect(wait=True)
    conn.subscribe(destination='/queue/request', id=1, ack='auto')
    
    print("[DISPATCHER] Waiting for request ... ")

    # Keep the listener active
    while True:
        time.sleep(60)
   
