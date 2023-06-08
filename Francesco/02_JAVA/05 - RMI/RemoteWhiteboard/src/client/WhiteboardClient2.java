package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.*;

public class WhiteboardClient2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			Whiteboard board = (Whiteboard)rmiRegistry.lookup("myWhiteboard");
			System.out.println ("Got reference < myWhiteboard > " );
			System.out.println ( board.toString() );
			
			Observer observer = new ObserverImpl(board);
			
			System.out.println ("\nObserver with ref: " );
			System.out.println ( observer.toString());
			
			board.attachObserver(observer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
