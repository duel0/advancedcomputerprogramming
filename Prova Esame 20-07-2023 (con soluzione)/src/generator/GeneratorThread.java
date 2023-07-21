package generator;

import java.rmi.RemoteException;

import order.OrderImpl;
import service.*;

public class GeneratorThread extends Thread{

    private IManager m;

    public GeneratorThread(IManager m){
        this.m=m;
    }

    public void run(){
        IOrder o = new OrderImpl(); // Scelte randomiche nel costruttore dell'ordine!
        System.out.println("[GENERATOR-THREAD] Genero ordine: "+o.getID()+" - "+o.getLocation()+" - "+o.getAdress());
        try {
            int response = m.setOrder(o);
            if(response==1){
                System.out.println("[GENERATOR-THREAD] Ordine "+o.getID()+": Il rider sta arrivando!!!");
            } else {
                System.out.println("[GENERATOR-THREAD] Ordine: "+o.getID()+": nessun rider trovato! Rimborso in arrivo (o forse no...)");
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
