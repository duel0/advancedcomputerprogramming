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

		ClientWorkerThread[] workers = new ClientWorkerThread[5];

		for(int i=0; i<5; i++){
			workers[i] = new ClientWorkerThread(dispatcher);
			workers[i].start();
		}

		for(int i=0; i<5; i++){
			try {
				workers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		

	}

}
