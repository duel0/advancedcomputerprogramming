package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;
import interfaces.ISportello;


public class SportelloServer {
	
	public static void main (String[] args) {
		
		try {
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IGestoreSportello gestore = (IGestoreSportello) rmiRegistry.lookup("gestore");
			
			ISportello sportello = new SportelloImpl();
			gestore.sottoscrivi(sportello);
			
			System.out.println("[SportelloServer] Sottoscritto sportello al gestore");
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
