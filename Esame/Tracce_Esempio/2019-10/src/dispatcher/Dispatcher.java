package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Dispatcher {
    public static void main(String[] args) {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher d = new DispatcherImpl();
            rmiRegistry.rebind("dispatcherO", d);
            System.out.println("[DISPATCHER] Rebind effettuato!");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
