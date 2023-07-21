import stomp, time

class MyListener(stomp.ConnectionListener):
    def __init__(self, conn) -> None:
        self.conn=conn
    def on_message(self, frame):
        print("Ricevo: ",frame.body)

if __name__ == "__main__":

    conn = stomp.Connection([('127.0.0.1',61613)])
    listener = MyListener(conn)
    conn.set_listener('',listener)
    conn.connect(wait=True)
    conn.subscribe(destination="/queue/prodcons",id=1,ack='auto')
    
    
    print("[CONSUMER] Pronto!")
    time.sleep(120)
    conn.disconnect()