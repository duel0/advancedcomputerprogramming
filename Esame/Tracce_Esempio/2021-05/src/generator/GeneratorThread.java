package generator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.AlertNotification;
import service.IManager;

public class GeneratorThread extends Thread{
    public void run(){
        try {
            
            Thread.sleep(3000);
            AlertNotification n = new AlertNotification();
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager stub = (IManager) rmiRegistry.lookup("myManager");
            stub.sendNotification(n);
            System.out.println("[GENERATOR] Invio component: "+n.getComponentID()+" con criticita': "+n.getCriticality());

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
