package client;

import java.rmi.registry.*;

import service.Hello;

public class HelloClient {
	private HelloClient(){}
	
	public static void main (String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry();
			Hello stub = (Hello) registry.lookup("Hello");
			String response = stub.sayHello();
			System.out.println("Response: "+response);
		} catch (Exception e) {
			System.err.println("Eccezione: "+e.toString());
			e.printStackTrace();
		}
	}
}
