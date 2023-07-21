package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinter extends Remote{
    void printDoc(String s)throws RemoteException;
}