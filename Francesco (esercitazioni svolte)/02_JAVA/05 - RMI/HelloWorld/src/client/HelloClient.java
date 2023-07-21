package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.Hello;

public class HelloClient {
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.getRegistry();
            Hello stub = (Hello)registry.lookup("Hello");

            String response = stub.sayHello();
            System.out.println("Response: "+response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
