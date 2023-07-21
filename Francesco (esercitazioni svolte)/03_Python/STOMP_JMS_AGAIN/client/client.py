import stomp, sys, time, random

class MyListener(stomp.ConnectionListener):
    def on_message(self, frame):

        print("[CLIENT] Ricevuto: ",frame.body)

if __name__=="__main__":
    
    conn = stomp.Connection([('127.0.0.1',61613)],auto_content_length=False)
    conn.set_listener('',MyListener())
    conn.connect(wait=True)
    conn.subscribe("/queue/response",id=1,ack='auto')
    
    print("Client connesso!")

    for i in range(10):
        if(i%2==0):
            request = 'deposita'
            val = random.randint(1,100)
            MSG = request+'-'+str(val)
        else:
            MSG='preleva'

        conn.send("/queue/request",MSG)
        print("[CLIENT] Ho inviato ",MSG)
    time.sleep(60)
    conn.disconnect()
