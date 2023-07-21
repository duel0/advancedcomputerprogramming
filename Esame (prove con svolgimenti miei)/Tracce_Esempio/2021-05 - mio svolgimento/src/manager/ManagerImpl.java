package manager;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.Vector;

import service.*;

public class ManagerImpl extends UnicastRemoteObject implements IManager{

    private Vector<SubscriberProxy> subs;

    public ManagerImpl() throws RemoteException{
        super();
        subs = new Vector<SubscriberProxy>();
    }
    @Override
    public synchronized void sendNotification(AlertNotification n) throws RemoteException {
        System.out.println("[MANAGER] Ricevo alert...");
        for(int i=0; i<subs.size(); i++){
            if(subs.get(i).getComponentID()==n.getComponentID()){
                subs.get(i).notifyAlert(n.getCriticality());
            }
        }
    }

    @Override
    public void subscribe(int componentID, int port) throws RemoteException {
        System.out.println("[MANAGER] Ricevo richiesta di iscrizione...");
        SubscriberProxy s = new SubscriberProxy(componentID, port);
        subs.add(s);
    }
    
}
