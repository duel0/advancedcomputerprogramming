import socket, sys

if __name__ == '__main__':

    try:
        port = int(sys.argv[1])
    except IndexError:
        print("Please, specify PORT arg...")

    assert port != "", 'specify port'

    # Server IP and port
    host = '127.0.0.1'

    # create socket and connect to the server
    s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    s.connect((host,port))

    # message to send
    message = "hello server"

    # send message
    s.send(message.encode('ascii'))

    # message received from server
    data = s.recv(1024)
    print('Received from the server :',str(data.decode('ascii')))

    s.close()


