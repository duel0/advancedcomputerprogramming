import logging
import threading
import random

logging.basicConfig(level=logging.DEBUG, format='[%(threadName)-0s] %(message)s')

CONSUMER = 'Consumer_'
PRODUCER = 'Producer_'
SIZE = 5
N = 10

def get(queue):
    return queue.pop(0)
def set(queue):
    val = random.randint(1,100)
    logging.debug("Produco: "+str(val))
    queue.append(val)

def produci(queue, mutex, spazioDisp, elemDisp):
    spazioDisp.acquire()
    with mutex:
        set(queue)
        logging.debug("Ho prodotto!")
    elemDisp.release()

def consuma(queue, mutex, spazioDisp, elemDisp):
    elemDisp.acquire()
    with mutex:
        elem = get(queue)
        logging.debug("Ho consumato: "+str(elem))
    spazioDisp.release()

def main():
    queue = []
    
    mutex = threading.Semaphore()
    spazioDisp = threading.Semaphore(SIZE)
    elemDisp = threading.Semaphore(0)

    producers = []
    consumers = []

    for i in range(N):
        p = threading.Thread(target=produci, name=PRODUCER+str(i), args=(queue,mutex,spazioDisp,elemDisp))
        p.start()
        producers.append(p)
    for i in range(N):
        p = threading.Thread(target=consuma, name=PRODUCER+str(i), args=(queue,mutex,spazioDisp,elemDisp))
        p.start()
        consumers.append(p)

    for i in range(N):
        producers[i].join()
    for i in range(N):
        consumers[i].join()

if __name__=="__main__":
    main()