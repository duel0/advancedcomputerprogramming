package client;

import dispatcher.*;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * uso: 		java client.Client IP porta
		 * per es.:		java client.Client 127.0.0.1 8000
		 */

		
		/*
		 * TODO: realizzare l'implementazione con N thread client
		 */
		
		IDispatcher dispatcher = new DispatcherProxy ( args[0], Integer.valueOf( args[1]) );
	
		for (int i=0; i<4; i++){
			
			ClientWorkerThread client = new ClientWorkerThread(dispatcher);
			client.start();
		}
		
	}

}
