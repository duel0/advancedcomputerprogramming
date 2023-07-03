package dispatcher;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.jms.MapMessage;

import printer.IPrinter;

public class DispatcherThread extends Thread{

    private MapMessage msg;

    public DispatcherThread(MapMessage m){
        msg=m;
    }

    public void run(){
        System.out.println("[DISPATCHER-THREAD] Avviato!");
        try {
            System.out.println("[DISPATCHER-THREAD] Documento: "+msg.getString("documento")+" per la printer "+msg.getString("printer"));
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IPrinter stub = (IPrinter)rmiRegistry.lookup(msg.getString("printer"));
            stub.printDoc(msg.getString("documento"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
