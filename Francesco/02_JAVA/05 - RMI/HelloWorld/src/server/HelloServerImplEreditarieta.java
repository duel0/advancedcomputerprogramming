package server;

import service.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class HelloServerImplEreditarieta extends UnicastRemoteObject implements Hello{
	public HelloServerImplEreditarieta() throws RemoteException{
		super();
	}
	
	public String sayHello() {
		System.out.println("sayHello() invoked on server");
		return "Hello, World!";
	}
}
