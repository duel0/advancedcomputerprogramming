package dispatcher;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IPrinter;

public class DispatcherThread extends Thread{
    private String d;
    private String p;

    public DispatcherThread(String doc, String pri){
        d=doc;
        p=pri;
    }

    public void run(){
        try {
            System.out.println("[DISPATCHER-THREAD] Stampo "+d+" in "+p);
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IPrinter stub = (IPrinter) rmiRegistry.lookup(p);
            stub.printDoc(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
