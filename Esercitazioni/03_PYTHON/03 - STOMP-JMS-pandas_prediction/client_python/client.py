import stomp, random, time
import pandas as pd
from scipy import stats
from matplotlib import pyplot as plt
import numpy as np


predictions = []

# Listener
class MyListener(stomp.ConnectionListener):

    def __init__(self):
         self.predictions = []

    def on_message(self, frame):
        
        # Printing the obtained response
        print('[CLIENT] Received response: "%s"' % frame.body)
        if "forecast" in frame.body:
            prediction = (frame.body).split('-')[1]
            self.predictions.append(prediction)
        
    def get_predictions(self):
         return self.predictions           


if __name__ == "__main__":

    # Create connection and set auto_conetnt_length to False in order to interact with a JMS application (using TextMessages)
    conn = stomp.Connection([('127.0.0.1', 61613)], auto_content_length=False)

    # Set the listener
    listener = MyListener()
    conn.set_listener('', listener)

    # Connect and subscribe to the queue 'response'
    conn.connect(wait=True)
    conn.subscribe(destination='/queue/response', id=1, ack='auto')

    years = []
    # Make the request
    forecast_reqs = 30
    for i in range(forecast_reqs):
        
        #if ( (i%2) == 0): 
        request = "forecast"

        # generate random years between 2021 and 2121
        year = random.randint(2021,2121)
        years.append(year)
        MSG = request + "-" + str(year)
        #else: 
        #      MSG = "get_mean"

        # Send the request on the queue 'request'
        conn.send('/queue/request', MSG)

        print("[CLIENT] Request: ", MSG)

    while True:
        time.sleep(10)
        print(listener.get_predictions())

        ## check if all forecast_reqs are completed 
        if (len(listener.get_predictions()) == forecast_reqs):
            
            ### plot
            print("PLT predictions")

            nyc = pd.read_csv('USH00305801-tmax-1-1-1895-2022.csv', skiprows = 4) # skiprows mi permette di bypassare righe che non mi interessano
            nyc.columns = ['Date', 'Temperature', 'Anomaly']
            nyc.Date = nyc.Date.floordiv(100) # remove 01 from Date
            
            fig, ax = plt.subplots()
            new_dates = pd.Series(years)
            ## faccio la conversione a float perchè le predizioni sono una lista (object)
            new_temps = pd.Series(listener.get_predictions(), dtype='float')

            ## create date and temperature series by extending series, 
            # new_dates and new_temps Series with a NaN Series with a size 
            # of orig date Series  
            
            nan_series = pd.Series(np.nan, index=range(0, len(nyc.Date)))
            
            ## I need new objects, concat does not work in-place
            newDates = pd.concat([nan_series, new_dates])
            newTemps = pd.concat([nan_series, new_temps])

            ## plot 2 scatter plots with the prediction one with alpha value
            ax.scatter(nyc.Date, nyc.Temperature); # plot temp vs date
            ax.scatter(newDates, newTemps, alpha=0.25)

            print(nyc.Date.dtype, nyc.Temperature.dtype)
            print(newDates.dtype, newTemps.dtype)

            ax.legend(['Original', 'Predicted'])
            
            ax.set_xlabel('Years')          
            ax.set_ylabel('Temperature [F°]')         

            #plt.show()

            ## mi conviene salvare il file in locale se volessi
            ## presentarlo in una webapp
            plt.savefig('prediction.png')
            plt.show()
            break

    conn.disconnect()
