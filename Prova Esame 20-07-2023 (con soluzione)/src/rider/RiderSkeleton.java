package rider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;
import service.IRider;

public class RiderSkeleton implements IRider{

    private IRider r;
    private int port;

    public RiderSkeleton(IRider r, int p){
        this.r=r;
        this.port=p;
    }

    public void runSkeleton(){
        try {
            // Lookup del manager e iscrivo il Rider ottenendo la location da RiderImpl (r.getLocation()) e il porto passato precedentemente al costruttore
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager m = (IManager) rmiRegistry.lookup("myManager");
            m.subscriber(r.getLocation(), port);
            System.out.println("[RIDER-"+r.getLocation()+"] Subscription effettuata!");

            ServerSocket ss = new ServerSocket(port);
            Socket s = null;
            
            while(true){
                s=ss.accept();
                RiderThread t = new RiderThread(s, this);
                t.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e){
            e.printStackTrace();
        }

    }

    @Override
    public int getLocation() {
        return r.getLocation();
    }

    @Override
    public void notifyOrder(int o, String add) {
        r.notifyOrder(o, add);
    }
    
}
