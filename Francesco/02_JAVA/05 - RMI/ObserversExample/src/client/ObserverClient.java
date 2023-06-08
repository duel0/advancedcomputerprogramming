package client;

import java.rmi.registry.*;

import service.*;

public class ObserverClient {
	public static void main ( String[] args ) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			MyService stub = (MyService) rmiRegistry.lookup("myservice");
			System.out.println ("Got reference < myservice > " );
			System.out.println ( stub.toString() );
			
			Observer observer = new ObserverImpl();
			System.out.println ("\nObserver with ref: " );
			System.out.println ( observer.toString());
			stub.attachObserver(observer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
