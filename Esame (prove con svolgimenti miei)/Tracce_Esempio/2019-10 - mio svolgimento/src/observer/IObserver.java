package observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote{
    void notifyReading() throws RemoteException;
}
