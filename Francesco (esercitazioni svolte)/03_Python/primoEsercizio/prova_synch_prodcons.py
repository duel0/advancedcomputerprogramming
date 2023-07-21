# Voglio implementare un'applicazione multithread che implementi produttore-consumatore utilizzando le variabili condition
import multiprocess
import threading
import logging
import time
from random import randint

# Possiamo usare una coda che viene gestita come una lista e può essere popolata da più produttori e consumatori
# Ricorda l'esempio di SO, ma qui usiamo un solo mutex.

CONSUMER='Consumer'
PRODUCER='Producer'
N_CONSUMERS=10
N_PRODUCERS=10
QUEUE_SIZE=5

logging.basicConfig(level=logging.DEBUG, format='[%(threadname-0s)] %(message)s',)

"""
class ConsumerThread(threading.Thread):
    def __init__(self, producer_cv, consumer_cv, queue, name):
        threading.Thread.__init__(self,name)
        super(self,name)
        self.producer_cv=producer_cv
        self.consumer_cv=consumer_cv
        self.queue=queue

    def run(self):
        logging.debug('Started') # => Timestamp [Consumer1] Started
"""

def an_item_is_available(queue):
     return not (len(queue)==0)
def a_space_is_available(queue):
     return not (len(queue)==QUEUE_SIZE)
def get_an_item(queue):
     return queue.pop(0)
def insert_an_item(queue):
     item = randint(0,100)
     queue.append(item)
     return item


class consumerThread(threading.Thread):
    def __init__(self, producer_cv, consumer_cv, queue, name):
        threading.Thread.__init__(self,name=name)
        # super().__init__(group=None,name=nome)
        self.producer_cv=producer_cv
        self.consumer_cv=consumer_cv
        self.queue=queue

    def run(self):
        logging.debug('Started') # => Timestamp [Consumer1] Started
        with self.consumer_cv: # Acquire del lock e alla fine lo rilascia
            # if o while? while perché signal e continue
            logging.debug('Lock acquisito!')
            while not an_item_is_available(self.queue):
                 logging.debug('Devo aspettare per la consumazione')
                 self.consumer_cv.wait()
            item=get_an_item(self.queue)
            logging.debug('Item consumato: %d',item)
            self.producer_cv.notify()
        logging.debug('Lock rilasciato!')

def produce_item(producer_cv, consumer_cv,queue):
    logging.debug('Started')
    with producer_cv:
         logging.debug('Lock acquisito!')
         while not a_space_is_available(queue):
              logging.debug('Devo aspettare la produzione')
              producer_cv.wait()
         item=insert_an_item(queue)
         logging.debug('Item prodotto: %d', item)
         consumer_cv.notify()
    logging.debug('Lock rilasciato!')
    
     

def main():
    # creiamo la coda

    queue = []

    # creiamo le variabili per la sincronizzazione, le variabili condition
    cv_lock = threading.Lock()

    producer_cv = threading.Condition(lock=cv_lock) # Altrimenti si crea il reentrant lock, stesso discorso di pthread.
    # Ricorda che possono entrare al più un producer e un consumer
    consumer_cv = threading.Condition(lock=cv_lock)

    consumers = []
    producers = []

    # Generare i consumatori, classe che eredita da thread
    

    for i in range(N_CONSUMERS):
            name = CONSUMER+str(i)

            ct = consumerThread(producer_cv, consumer_cv, queue, name)
            
            ct.start()

            consumers.append(ct)

            

    # Generare i produttori, callable objects

    for i in range(N_PRODUCERS):
        pt = threading.Thread(target = produce_item, name = PRODUCER+str(i), args=(producer_cv, consumer_cv,queue))

        pt.start()

        producers.append(pt)

    
    for i in range(N_CONSUMERS):
         consumers[i].join()
    for i in range(N_PRODUCERS):
         producers[i].join()

if __name__=='__main__':
    main()



