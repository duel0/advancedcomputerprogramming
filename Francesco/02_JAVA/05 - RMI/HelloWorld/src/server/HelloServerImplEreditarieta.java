package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.Hello;

public class HelloServerImplEreditarieta extends UnicastRemoteObject implements Hello{

    protected HelloServerImplEreditarieta() throws RemoteException{
        super();
    }

    public String sayHello(){
        System.out.println("sayHello() invocata sul server...");
        return "Hello, World!";
    }
    
}
