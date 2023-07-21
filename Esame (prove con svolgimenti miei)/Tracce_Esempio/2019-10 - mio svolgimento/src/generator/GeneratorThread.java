package generator;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dispatcher.IDispatcher;

public class GeneratorThread extends Thread{

    private IDispatcher d;

    public GeneratorThread(){
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            d = (IDispatcher) rmiRegistry.lookup("dispatcherO");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void run(){
        try {
            for(int i=0; i<3; i++){
                Reading r = new Reading();
                Thread.sleep(5000);
                d.setReading(r);
                System.out.println("[GENERATOR] setReading invocato con "+r.getTipo()+": "+r.getValore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
