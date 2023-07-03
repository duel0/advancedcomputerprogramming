package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;

public class GestoreServer {
	
	public static void main (String[] args) {
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IGestoreSportello gestore = new GestoreSportelloImpl();

			rmiRegistry.rebind("GestoreSportello", gestore);
			System.out.println("Gestore pronto");



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		
	}

}
