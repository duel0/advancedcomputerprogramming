package client;

import whiteboard.*;

import java.rmi.registry.*;

public class WhiteboardClient1 {

	public static void main ( String[] args ){
		try{
			

			
			
			/*
			 * 1-Ottiene il riferimento alla lavagna remota.
			 */
			
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			Whiteboard board = (Whiteboard)rmiRegistry.lookup("myWhiteboard");
			System.out.println ("Got reference < myWhiteboard > " );
			System.out.println ( board.toString() );
			
			
			/*
			 * Aggiunge alla lavagna remota 4 Shape selezionate in maniera casuale 
			 * tra Triangoli e Quadrati ogni 10 secondi 
			 * 
			 * TODO (esercizio): Aggiungere una terza implementazione Shape (per es., Circle)
			 * 
			 */
			
			Shape s; int x;
			
			for ( int i =0; i < 4; i++){
				x = (int)(1+Math.random()*10);
				
				if ( x <= 5)
					s=new Triangle();
				else
					s=new Square();
				
				System.out.println ("\nAdding the shape (" + x + ")" + s.toString() );
				
				//invocazione remota. L'argomento Shape e' un oggetto non-remoto 
				board.addShape(s);
				
				Thread.sleep(10000);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
