package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;

public class Client {
	
	public static void main (String[] args) {
		
		int T = 10;
		int R = 10;
		try{
			ClientThread[] threads = new ClientThread[T];
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IGestoreSportello stub = (IGestoreSportello) rmiRegistry.lookup("GestoreSportello");

			for(int i = 0; i < T; i++) {
				threads[i] = new ClientThread(R, stub);
				threads[i].start();
			}

			for(int i = 0; i < T; i++) {
				threads[i].join();
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
