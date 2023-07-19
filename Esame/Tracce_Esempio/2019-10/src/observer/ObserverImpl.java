package observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import dispatcher.IDispatcher;

public class ObserverImpl extends UnicastRemoteObject implements IObserver{
    private String f;
    public ObserverImpl(String filename) throws RemoteException{
        f=filename;
    }
    public void notifyReading() throws RemoteException{
        System.out.println("[OBSERVER] notifyReading() invocata!");
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher stub = (IDispatcher) rmiRegistry.lookup("dispatcherO");
            int lettura = stub.getReading();
            System.out.println("[OBSERVER] Leggo: "+lettura);
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(String.valueOf(lettura)+"\n");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}