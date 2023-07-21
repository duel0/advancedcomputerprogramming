package dispatcher;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.MapMessage;

import service.IPrinter;

public class DispatcherThread extends Thread{

    private MapMessage mm;

    public DispatcherThread(MapMessage m){
        mm=m;
    }

    public void run(){
        try {
            System.out.println("[DISPATCHER-THREAD] Messaggio ricevuto! Processing...");
            System.out.println("[DISPATCHER-THREAD] Documento: "+mm.getString("documento"));
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IPrinter printer = (IPrinter) rmiRegistry.lookup(mm.getString("printer"));
            printer.printDoc(mm.getString("documento"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
