import socket, sys, random, time

if __name__ == "__main__":
    try:
        PORT = sys.argv[1]
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        for i in range(100):
            time.sleep(random.randint(0,4))
            msg = "produci-"+str(random.randint(1,100))
            print("[PRODUCER] Invio messaggio: "+msg)
            s.sendto(msg.encode(),('localhost',int(PORT)))
        s.close()
    except IndexError:
        print("Dammi il porto!")

    