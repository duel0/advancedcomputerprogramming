package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

import service.*;

public class ServerImpl extends UnicastRemoteObject implements MyService {
	
	private Vector<Observer> attachedObservers;	//contiene gli observer registrati
	
	protected final static long serialVersionUID = 10;
	
	public ServerImpl () throws RemoteException {
		attachedObservers = new Vector<Observer> ();
	}
	
	public void service_method(){

		System.out.println("service_method() invoked!");

		// notifica gli observer che un client ha invocato il metodo remoto service_method()
		notifyAllObservers();
	}
	
	public void attachObserver ( Observer observer) throws RemoteException{
		
		System.out.println ("\nNew observer attached! \n " + observer.toString() );
		attachedObservers.add( observer );
		
	}
	
	/*
	 * Metodo privato per la notifica degli observer registrati.
	 * NOTA: gli Observer sono oggetti remoti
	 */

	private void notifyAllObservers(){

		System.out.println ("(new shape, notify observers! )" );
		int size = attachedObservers.size();
		
		for ( int i =0; i<size; i++){	
			try{
				attachedObservers.get(i).observerNotify();
			}catch(RemoteException e ){
				e.printStackTrace();
			}
	
		}	
	}
}
