package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.MyService;

public class InvokerClient {
    public static void main(String[] args) {
        try{
            Registry rmiregistry = LocateRegistry.getRegistry();
            MyService stub = (MyService)rmiregistry.lookup("myservice");
            System.out.println("Got Reference!");
            stub.service_method();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
