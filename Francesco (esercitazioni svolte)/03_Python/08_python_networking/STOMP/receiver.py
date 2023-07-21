import time
import stomp

class MyListener(stomp.ConnectionListener):
    def __init__(self, conn):
        self.conn=conn
    def on_message(self,frame):
        print("Ricevuto: "+frame.body)

if __name__=="__main__":
    conn = stomp.Connection([('127.0.0.1',61613)])
    conn.set_listener('', MyListener(conn))

    conn.connect(wait=True)
    conn.subscribe(destination='/topic/mytesttopic', id=1, ack='auto')
    print('Receiver waiting for message...')

    time.sleep(60)

    conn.disconnect()