package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.*;

public class ServerImpl extends UnicastRemoteObject implements MyService{

    private Vector<Observer> attachedObservers;
    protected final static long serialVersionUID=10;

    public ServerImpl() throws RemoteException{
        attachedObservers = new Vector<Observer>();
    }

    
    public void attachObserver(Observer observer) throws RemoteException {
        
       System.out.println("New observer attached\n"+observer.toString());
       attachedObservers.add(observer);
    }

    
    public void service_method(){
        System.out.println("service_methond() invocato!");
        notifyAllObservers();
    }

    private void notifyAllObservers(){
        System.out.println("Notify observers!");
        int size = attachedObservers.size();
        for(int i=0; i<size; i++){
            try{
                attachedObservers.get(i).observerNotify();
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }
}
