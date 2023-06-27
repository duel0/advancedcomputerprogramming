package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;

public class Client {
	
	public static void main (String[] args) {
		
		int T = 10;
		int R = 10;
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			IGestoreSportello gestore = (IGestoreSportello) rmiRegistry.lookup("gestore");
			
			ClientThread[] threads = new ClientThread[T];
			
			for (int i = 0; i < T; i++) {
				threads[i] = new ClientThread(R, gestore);
				threads[i].start();
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}
