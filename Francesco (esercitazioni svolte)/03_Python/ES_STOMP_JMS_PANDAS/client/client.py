import stomp, random, time
import pandas as pd
from matplotlib import pyplot as plt
import numpy as np

predictions = []

class MyListener(stomp.ConnectionListener):
    def __init__(self):
        self.predictions=[]
    def on_message(self, frame):
        print("[CLIENT] Ricevo una risposta: ",frame.body)
        if "forecast" in frame.body:
            prediction = (frame.body).split('-')[1]
            self.predictions.append(prediction)
    def get_predictions(self):
        return self.predictions
    
if __name__=="__main__":
    conn = stomp.Connection([('127.0.0.1',61613)], auto_content_length=False)
    listener = MyListener()
    conn.set_listener('',listener)
    conn.connect(wait=True)
    conn.subscribe(destination='/queue/response',id=1,ack='auto')
    years = []
    forecast_reqs=30
    for i in range(forecast_reqs):
        request = "forecast"
        year = random.randint(2021,2121)
        MSG = request+"-"+str(year)
        conn.send("/queue/request",MSG)
        print("[CLIENT] Richiesta: ",MSG)
    while True:
        time.sleep(10)
        print(listener.get_predictions())
        if(len(listener.get_predictions())==forecast_reqs):
            print("PLT predictions")

            nyc = pd.read_csv('USH00305801-tmax-1-1-1895-2022.csv', skiprows=4)
            nyc.columns = ['Date', 'Temperature', 'Anomaly']
            nyc.Date = nyc.Date.floordiv(100)

            fig, ax = plt.subplots()
            new_dates = pd.Series(years)
            new_temps = pd.Series(listener.get_predictions(),dtype='float')

            nan_series = pd.Series(np.nan, index=range(0,len(nyc.Date)))

            newDates=pd.concat([nan_series, new_dates])
            newTemps = pd.concat([nan_series, new_temps])

            ax.scatter(nyc.Date.dtype, nyc.Temperature.dtype)
            print(newDates.dtype, newTemps.dtype)

            ax.legend(['Original','Predicted'])
            ax.set_xlabel('Years')
            ax.set_ylabel('Temperature [F]')
            plt.savefig('prediction.png')
            plt.show()
            break
    conn.disconnect()