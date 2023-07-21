package manager;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Manager {
    public static void main(String[] args) {
        try {
            // Rebind
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager m = new ManagerImpl();
            rmiRegistry.rebind("myManager", m);
            System.out.println("[MANAGER] Rebind effettuato!");
        } catch (AccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
