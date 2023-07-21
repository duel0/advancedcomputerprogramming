package client;


public class Receiver extends Thread {
	private int id;

	public Receiver(int i){
		id=i;
	}
	@Override
	public void run() {
		System.out.println("[RECEIVER] Ricevo "+id);
		
	}

}
