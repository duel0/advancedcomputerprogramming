package client;

import java.rmi.registry.*;

import service.*;

/*
 * ObserverClient agisce da Server per il servizio remoto di callback
 */

public class ObserverClient {

	public static void main ( String[] args ){
		try{

			/*
			 * 1-Ottiene il riferimento al servizio remoto
			 * 2-Crea l'oggetto callback (ObserverImpl)
			 * 3-Invoca il metodo remoto di MyService per registrare l'observer (attachObserver)
			 */
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			MyService stub_myservice = (MyService)rmiRegistry.lookup("myservice");
			System.out.println ("Got reference < myservice > " );
			System.out.println ( stub_myservice.toString() );
		
			/*
			 * il callback object viene creato lato client e poi verrà passato come parametro
			 * al metodo remoto esportato dal server, ovvero attachObserver
			 */

			Observer observer = new ObserverImpl();
			
			System.out.println ("\nObserver with ref: " );
			System.out.println ( observer.toString());
		
			stub_myservice.attachObserver(observer);
					
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
