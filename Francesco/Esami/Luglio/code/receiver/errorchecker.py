import sys
import stomp
import time

class MyListener(stomp.ConnectionListener):
    def __init__(self, conn, string):
        self.conn=conn
        self.string=string
    def on_message(self, frame):
        print("Ricevuto: ",frame.body)
        if self.string in frame.body:
            print("Mo lo scrivo pure!")
            with open("error.txt","a") as file:
                file.write(frame.body+"\n")

if __name__=="__main__":
    try:
        string = sys.argv[1]
    except IndexError:
        print("Dammi la frase!")
        sys.exit()
    
    conn = stomp.Connection([('127.0.0.1',61613)])
    conn.set_listener('',MyListener(conn,string))
    conn.connect(wait=True)
    conn.subscribe(destination="/queue/error",id=1, ack='auto')
    print("Receiver waiting for messages...")
    time.sleep(60)
    conn.disconnect()