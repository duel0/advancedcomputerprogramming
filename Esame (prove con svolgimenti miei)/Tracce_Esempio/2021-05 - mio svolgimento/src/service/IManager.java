package service;

import java.rmi.*;

public interface IManager extends Remote{
    void sendNotification(AlertNotification n) throws RemoteException;
    void subscribe(int componentID, int port) throws RemoteException;
}
