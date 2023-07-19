package subscriber;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;
import service.ISubscriber;

public class Subscriber {
    public static void main(String[] args) {
        if(args.length!=3){
            System.out.println("Mancano i parametri bro!");
            return;
        }
        try {
            
            ISubscriber sub = new SubscriberImpl(args[2]);
            SubscriberSkeleton skeleton = new SubscriberSkeleton(sub,Integer.parseInt(args[1]));
            
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager m = (IManager) rmiRegistry.lookup("myManager");
            System.out.println("[SUBSCRIBER] Mi iscrivo al manager...");
            m.subscribe(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            skeleton.runSkeleton();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
