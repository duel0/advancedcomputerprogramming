import sys
import time
import stomp

class MyListener(stomp.ConnectionListener):
    def __init__(self, conn) -> None:
        self.conn=conn
    def on_message(self, frame):
        print("Ricevuto: ",frame.body)
        if "1" in frame.body:
            with open("info.txt","a") as file:
                file.write(frame.body+"\n")

if __name__=="__main__":
    
    conn = stomp.Connection([('127.0.0.1',61613)])
    conn.set_listener('',MyListener(conn))
    conn.connect(wait=True)
    conn.subscribe(destination="/queue/info",id=1, ack='auto')
    print("Receiver waiting for messages...")
    time.sleep(60)
    conn.disconnect()