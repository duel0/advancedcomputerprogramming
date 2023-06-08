package client;

import java.rmi.registry.*;

import service.*;

public class InvokerClient {
	public static void main ( String[] args ) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			MyService stub = (MyService) rmiRegistry.lookup("myservice");
			System.out.println ("Got reference < myservice > " );
			System.out.println ( stub.toString() );
			
			stub.service_method();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
