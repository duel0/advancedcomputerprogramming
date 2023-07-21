package observer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dispatcher.IDispatcher;

public class Observer {
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("I parametriiii");
            return;
        }
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher stub = (IDispatcher) rmiRegistry.lookup("dispatcherO");
            IObserver o = new ObserverImpl(args[1]);
            stub.attach(args[0], o);
            System.out.println("[OBSERVER] Attach effettuato!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
