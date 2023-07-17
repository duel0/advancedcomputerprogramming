package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccia.IService;

public class Server {

	public static void main(String[] args) {
		
		try {
			Registry rmiregistry = LocateRegistry.getRegistry();
			IService myService = new Service();
			rmiregistry.rebind("service", myService);
			System.out.println("Rebind effettuato!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
