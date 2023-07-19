package manager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Manager {
    public static void main(String[] args) {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager m = new ManagerImpl();
            rmiRegistry.rebind("myManager", m);
            System.out.println("Manager registrato!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
