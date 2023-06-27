package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;


public class GestoreServer {
	
	public static void main (String[] args) {
		
		Registry rmiRegistry;
		try {
			rmiRegistry = LocateRegistry.getRegistry();
			
			IGestoreSportello gestore = new GestoreSportelloImpl();
			rmiRegistry.rebind("gestore", gestore);
			
			System.out.println("[GestoreServer] Gestore avviato");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
