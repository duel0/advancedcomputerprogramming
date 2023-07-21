package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.MyService;

public class Server {
    public static void main(String[] args) {
        try{
            System.out.println("Creo l'oggetto...");
            MyService myservice = new ServerImpl();
            System.out.println(myservice.toString()+"\n");

            Registry rmiregistry = LocateRegistry.getRegistry();
            rmiregistry.rebind("myservice", myservice);
            System.out.println("Oggetto registrato con nome myservice");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
