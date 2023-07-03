package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccia.IService;

public class Server {

	public static void main(String[] args) {
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IService myService = new Service();
			rmiRegistry.rebind("Service", myService);
			System.out.println("Service pronto!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
