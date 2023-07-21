package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Generator {
    public static void main(String[] args) {
        System.out.println("[GENERATOR] Inizio");
        int NTHREADS = 3;
        try {

            // Lookup
            Registry rmiRegistry = LocateRegistry.getRegistry();
            
            IManager manager = (IManager) rmiRegistry.lookup("myManager");

            GeneratorThread[] threads = new GeneratorThread[NTHREADS];
            // Starto i thread
            for(int i=0; i<NTHREADS; i++){
                threads[i] = new GeneratorThread(manager);
                threads[i].start();
            }
            // Join
            for(int i=0; i<NTHREADS; i++){
                threads[i].join();
            }
            
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
