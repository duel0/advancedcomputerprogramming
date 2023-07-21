import stomp, random, time

class MyListener(stomp.ConnectionListener):

    ## override di on_message

    def on_message(self, frame):
        print('[CLIENT] received resp: %s' % frame.body) # puoi usare logging


if __name__ == '__main__':

    conn = stomp.Connection([('localhost',61613)])

    conn.set_listener('',MyListener())

    conn.connect(wait=True)

    conn.subscribe(destination='/queue/response', id=1, ack='auto') # Non dobbiamo comunicare con app JMS

    for i in range(10):
        if(i%2==0):
            request = 'deposita'
            id = random.randint(1,100)

            # stringa separata da un trattino che funge da separatore

            MSG = request + "-" + str(id)
        else:
            MSG = "preleva"
        conn.send('/queue/response',MSG)
        print('[CLIENT] Request: ',MSG)

    # ... fai while true con una sleep(60)
    # se impostiamo un numero finito di interazioni, facciamo conn.disconnect
    while True:
        time.sleep(60)
    # Ora apre la dashboard di activeMQ