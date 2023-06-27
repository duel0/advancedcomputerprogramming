from interface import Service
import socket, sys
import multiprocess as mp
import pandas as pd
from scipy import stats


# Process function
def proc_fun(c, service):

    # Receive the request
    data = c.recv(1024)

    # Chek the type of request and invoke the proper Service method
    # NOTE: the operator "in" is used, since Java adds extra characters when sending String over socket, which prevents the exact match
    if "get_mean" in str(data.decode()) :

        result = service.get_mean()

    elif "forecast" in str(data.decode()) :

        year = str(data.decode()).split('-')[1]
        result = service.forecast(year)
    
    # Send the response
    # NOTE: It is required to add "\n" at the end of the String in order to allow Java application to receive the data
    string_to_send = (str(result)+"\n")
    c.send(string_to_send.encode())

    # Close connection
    c.close()

# Skeleton
class ServiceSkeleton(Service):

    def __init__(self, port):
        self.port = port

    def forecast (self, year):
        pass

    def get_mean(self):
        pass

    def run_skeleton(self):
        
        host = 'localhost'

        # Create and bind the socket
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((host, self.port))

        s.listen(5)
        print("Socket is listening")

        while True:

            # Establish a connection with client
            c, addr = s.accept()

            # Start a new process to serve the reqest
            p = mp.Process(target=proc_fun, args=(c, self))
            p.start()

        s.close()

# Service Implementation
class ServiceImpl(ServiceSkeleton):
    
    def forecast(self, year):

        #self.queue.put(year)

        print("[SERVER-IMPL] compute regression...")
        # compute regression
        nyc = pd.read_csv('USH00305801-tmax-1-1-1895-2022.csv', skiprows = 4) # skiprows mi permette di bypassare righe che non mi interessano
        nyc.columns = ['Date', 'Temperature', 'Anomaly']
        nyc.Date = nyc.Date.floordiv(100) # remove 01 from Date
        linear_regression = stats.linregress(x=nyc.Date, y=nyc.Temperature)
        prediction = linear_regression.slope * int(year) + linear_regression.intercept 

        print("[SERVER-IMPL] Forecasted year: " + year + " prediction: " + str(prediction))

        return prediction
    
    def get_mean(self):

        #result = self.queue.get()
        print("[SERVER-IMPL] compute mean...")

        nyc = pd.read_csv('USH00305801-tmax-1-1-1895-2022.csv', skiprows = 4) # skiprows mi permette di bypassare righe che non mi interessano
        nyc.columns = ['Date', 'Temperature', 'Anomaly']
        nyc.Date = nyc.Date.floordiv(100) # remove 01 from Date
        pd.set_option('precision', 2)
        mean = nyc.Temperature.describe().mean()

        print("[SERVER-IMPL] get_mean: ", mean)
        
        return mean


if __name__ == "__main__":

    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Please, specify PORT arg")
    
    print("Server running ... ")

    # Create the Service and run the Skeleton
    
    serviceImpl = ServiceImpl(int(PORT))
    serviceImpl.run_skeleton()
