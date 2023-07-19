package dispatcher;

import java.rmi.Remote;
import java.rmi.RemoteException;

import generator.IReading;
import observer.IObserver;

public interface IDispatcher extends Remote{
    void attach(String tipo, IObserver o) throws RemoteException;
    void setReading(IReading r) throws RemoteException;
    int getReading() throws RemoteException;
}
