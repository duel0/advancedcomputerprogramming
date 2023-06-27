package client;

import whiteboard.*;

import java.rmi.registry.*;

/*
 * WhiteboardClient2 agisce da Server per il servizio remoto di callback
 */

public class WhiteboardClient2 {

	public static void main ( String[] args ){
		try{
			
			
			
			/*
			 * 1-Ottiene il riferimento alla lavagna remota.
			 * 2-Crea l'oggetto callback (ObserverImpl)
			 * 3-Invoca il metodo remoto di Witheboard per registrare l'observer 
			 */
			
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			Whiteboard board = (Whiteboard)rmiRegistry.lookup("myWhiteboard");
			System.out.println ("Got reference < myWhiteboard > " );
			System.out.println ( board.toString() );
		
			/*
			 * il callback object viene creato lato client e poi verrà passato come parametro
			 * al metodo remoto esportato dal server, ovvero attachObserver.
			 * Quindi il client non dovrà fare una bind dell'observer
			 */

			Observer observer = new ObserverImpl(board);
			
			System.out.println ("\nObserver with ref: " );
			System.out.println ( observer.toString());
		
			board.attachObserver(observer);
					
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
