package client;

import dispatcher.IDispatcher;

public class ClientWorkerThread extends Thread {
 
	private IDispatcher dispatcher; 
	
	public ClientWorkerThread (IDispatcher d){
		dispatcher = d; 
    }
	
    public void run(){

        int x = (int)(Math.random()*4);

        System.out.println ("[Thread ID:" + this.getId() + "] " + "[CLN] invio comando # " + x );
     
        dispatcher.sendCmd(x);
    }
}
