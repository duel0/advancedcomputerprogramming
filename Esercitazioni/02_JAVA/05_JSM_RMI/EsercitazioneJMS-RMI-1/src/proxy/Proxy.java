package proxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaccia.IService;

public class Proxy {
	
	
	public static void main(String[] args) {
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IService service = (IService) rmiRegistry.lookup("servizio");
			
			Receiver receiver = new Receiver(service);
			
			Thread t = new Thread(receiver);
			t.start();
			
			System.out.println("[PROXY] Avviato thread per gestione richieste");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
