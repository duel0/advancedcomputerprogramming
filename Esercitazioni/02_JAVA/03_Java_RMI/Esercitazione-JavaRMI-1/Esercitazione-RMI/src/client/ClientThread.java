package client;

import java.rmi.RemoteException;
import java.util.Random;

import interfaces.IGestoreSportello;


public class ClientThread extends Thread {
	
	private int requests;
	private IGestoreSportello gestore;
	
	
	public ClientThread(int requests, IGestoreSportello gestore) {
		
		this.requests = requests;
		this.gestore = gestore;
	}
	
	public void run() {
		
		Random rand = new Random();
		
		for (int i = 0; i < requests; i++) {
			
			try {
				
				Thread.sleep((rand.nextInt(3) + 1) * 1000);
				
				int idCliente = rand.nextInt(100)+1;
				
				boolean result = gestore.sottoponiRichiesta(idCliente);
				
				System.out.println("[ClientThread] Richiesta servita con esito " + result);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
