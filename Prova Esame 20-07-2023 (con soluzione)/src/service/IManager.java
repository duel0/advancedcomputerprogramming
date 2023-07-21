package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IManager extends Remote{
    int setOrder(IOrder o) throws RemoteException;
    void subscriber(int location, int port) throws RemoteException;
}
