package server;


import java.rmi.registry.*;

import service.*;

public class Server {
	
	public static void main ( String[] args ){
	
		try{
			
			/*
			 * Creazione e registrazione dell'oggetto remoto. 
			 */
			
			System.out.println ("Creating the object...");
			MyService myservice = new ServerImpl();
			
			System.out.println (myservice.toString() + "\n");
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry.rebind( "myservice" , myservice);
			System.out.println ("Object registered with name < myservice > \n" );
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
