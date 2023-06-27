package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccia.IService;


public class Server {

	public static void main(String[] args) {
		
		try {
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			IService servizio = new Service();
			rmiRegistry.rebind("servizio", servizio);
			
			System.out.println("[SERVER] Server avviato");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
