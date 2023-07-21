package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.*;

public class ManagerImpl extends UnicastRemoteObject implements IManager{

    private Vector<IRider> riders;


    public ManagerImpl() throws RemoteException{
        riders = new Vector<IRider>();
    }

    @Override
    public synchronized int setOrder(IOrder o) throws RemoteException { // Synchronized: eseguito in mutua esclusione
        

        // Riderproxy mantiene l'info della location e del porto
        // Controllo, appena lo trovo gli notifico l'ordine e interrompo setOrder ritornando il parametro
        // Altrimenti, al termine del ciclo (rider non trovato), ritorno -1

        if(riders.size()==0){
            System.out.println("[MANAGER] Ricevo setOrder ma non ho rider registrati...");
            return -1;
        }

        System.out.println("[MANAGER] Ricevo l'ordine: "+o.getID()+" - "+o.getLocation()+" - "+o.getAdress());

        for(int i=0; i<riders.size(); i++){
            if(riders.get(i).getLocation()==o.getLocation()){
                System.out.println("[MANAGER] Rider per l'ordine "+o.getID()+" trovato!");
                riders.get(i).notifyOrder(o.getID(), o.getAdress());
                return 1;
            }
        }

        System.out.println("[MANAGER] Rider per l'ordine "+o.getID()+" non trovato...");
        return -1;

    }

    @Override
    public void subscriber(int location, int port) throws RemoteException {
        System.out.println("[MANAGER] Nuova subscription! Rider: "+location+" al porto "+port);

        // Passo al costruttore i parametri necessari per l'invio su socket e per la gestione da parte del manager

        IRider rider = new RiderProxy(location, port);
        riders.add(rider);
    }
}
