import stomp, random, time

class MyListener(stomp.ConnectionListener):
    def on_message(self, frame):
        print("[CLIENT-LISTENER] Ricevo il messaggio: ",frame.body)

if __name__ == "__main__":

    conn = stomp.Connection([('127.0.0.1',61613)], auto_content_length=False)
    
    conn.set_listener('', MyListener())
    conn.connect(wait=True)
    conn.subscribe(destination='/queue/response',id=1, ack='auto')

    for i in range(10):
        if(i%2==0):
            op = "deposita"
            num = random.randint(1,100)
            MSG = op+"-"+str(num)
        else:
            MSG = "preleva"
        
        conn.send('/queue/request',MSG)
        print("[CLIENT] Invio: ",MSG)
    while True:
        time.sleep(60)
    conn.disconnect()