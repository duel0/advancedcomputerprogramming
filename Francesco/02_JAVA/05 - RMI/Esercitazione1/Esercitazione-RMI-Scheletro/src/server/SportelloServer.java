package server;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;
import interfaces.ISportello;

public class SportelloServer {
	
	public static void main (String[] args) {
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			ISportello sportello = new SportelloImpl();

			IGestoreSportello stub = (IGestoreSportello) rmiRegistry.lookup("GestoreSportello");

			stub.sottoscrivi(sportello);

			System.out.println("Sportello pronto");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
