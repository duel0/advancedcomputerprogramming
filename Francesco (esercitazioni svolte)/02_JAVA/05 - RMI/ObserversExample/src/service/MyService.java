package service;

import java.rmi.*;

public interface MyService extends Remote{
    
    public void attachObserver(Observer observer) throws RemoteException;

    public void service_method() throws RemoteException;

}