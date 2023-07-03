package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.*;

public class ObserverClient {
    public static void main(String[] args) {
        try{
            Registry rmiregistry = LocateRegistry.getRegistry();
            MyService stub = (MyService) rmiregistry.lookup("myservice");
            System.out.println("Ottenuta la reference!");
            Observer observer = new ObserverImpl();
            System.out.println("Nuovo observer");
            stub.attachObserver(observer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
