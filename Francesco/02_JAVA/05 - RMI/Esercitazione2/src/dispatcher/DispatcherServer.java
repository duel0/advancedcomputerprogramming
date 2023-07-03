package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;


public class DispatcherServer {
    public static void main(String[] args) {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = new DispatcherImpl();
            rmiRegistry.rebind("dispatcher", dispatcher);
            System.out.println("[DISPATCHER] Rebind effettuato...");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
