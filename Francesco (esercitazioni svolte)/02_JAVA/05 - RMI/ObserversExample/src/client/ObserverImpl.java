package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.Observer;

public class ObserverImpl extends UnicastRemoteObject implements Observer{
    protected ObserverImpl() throws RemoteException{
        super();
    }

    private static final long serialVersionUID = 1L;

    public void observerNotify() throws RemoteException{
        System.out.println("Observernotify invocata!");
    }
}
