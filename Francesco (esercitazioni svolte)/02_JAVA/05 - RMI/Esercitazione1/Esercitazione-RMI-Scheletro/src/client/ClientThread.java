package client;


import interfaces.IGestoreSportello;


public class ClientThread extends Thread {
	
	private int requests;
	private IGestoreSportello gestore;

	public ClientThread(int requests, IGestoreSportello gestore) {
		this.requests = requests;
		this.gestore = gestore;		
	}
	
	public void run() {
		
		for(int i = 0; i < requests; i++) {
			try {
				Thread.sleep((int) (Math.random()*3+1) * 1000);
				int idCliente = (int) (Math.random() * 101);
				gestore.sottoponiRichiesta(idCliente);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
