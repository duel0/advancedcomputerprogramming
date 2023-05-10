package server;


import java.rmi.registry.*;
import whiteboard.*;

public class WhiteboardServer {
	
	public static void main ( String[] args ){
	
		try{
			
			/*
			 * Creazione e registrazione dell'oggetto remoto. 
			 */
			
			System.out.println ("Creating the object...");
			Whiteboard board = new WhiteboardImpl();
			
			System.out.println (board.toString() + "\n");
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry.rebind( "myWhiteboard" , board);
			System.out.println ("Object registered with name < myWhiteboard > \n" );
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
