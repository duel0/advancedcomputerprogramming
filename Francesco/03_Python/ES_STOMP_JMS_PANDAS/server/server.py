from interface import Service
import socket, sys
import multiprocess as mp
import pandas as pd
from scipy import stats

def proc_fun(c, service):
    data = c.recv(1024)

    if "get_mean" in str(data.decode()):
        result = service.get_mean()
    elif "forecast" in str(data.decode()):
        year = str(data.decode()).split('-')[1]
        result = service.forecast(year)
    string_to_send=(str(result)+"\n")
    c.send(string_to_send.encode())
    c.close()

class ServiceSkeleton(Service):
    def __init__(self, port):
        self.port=port
    def forecast(self, message):
        pass
    def get_mean(self):
        pass
    def run_skeleton(self):
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(('localhost',self.port))
        s.listen(5)
        print("Socket ascolta...")
        while True:
            conn, addr = s.accept()
            p = mp.Process(target=proc_fun, args=(conn, self))
            p.start()
        s.close()

class ServiceImpl(ServiceSkeleton):
    def forecast(self, year):
        print("[SERVER-IMPL] Calcolo regressione...")
        nyc = pd.read_csv('USH00305801-tmax-1-1-1895-2022.csv', skiprows = 4)
        nyc.columns = ['Date', 'Temperature', 'Anomaly']
        nyc.Date = nyc.Date.floordiv(100)
        linear_regression = stats.linregress(x=nyc.Date, y=nyc.Temperature)
        prediction = linear_regression.slope * int(year) + linear_regression.intercept
        print("[SERVER-IMPL] Forecasted year: " + year + " prediction: " + str(prediction))
        return prediction
    def get_mean(self):
        print("[SERVER-IMPL] compute mean...")

        nyc = pd.read_csv('USH00305801-tmax-1-1-1895-2022.csv', skiprows = 4) # skiprows mi permette di bypassare righe che non mi interessano
        nyc.columns = ['Date', 'Temperature', 'Anomaly']
        nyc.Date = nyc.Date.floordiv(100) # remove 01 from Date
        pd.set_option('precision', 2)
        mean = nyc.Temperature.describe().mean()

        print("[SERVER-IMPL] get_mean: ", mean)
        
        return mean
    
if __name__=="__main__":
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("O PORTTT")
    print("Server running...")
    skeleton = ServiceImpl(int(PORT))
    skeleton.run_skeleton()