package server;
import service.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ServerImpl extends UnicastRemoteObject implements MyService{
	private Vector<Observer> attachedObservers;
	
	protected final static long serialVersionUID = 10;
	
	public ServerImpl() throws RemoteException{
		attachedObservers = new Vector<Observer>();
	}
	
	public void service_method() {
		System.out.println("service_method() invoked!");
		notifyAllObservers();
	}
	
	public void attachObserver(Observer observer) throws RemoteException{
		System.out.println("\nNew observer attached\n"+observer.toString());
		attachedObservers.add(observer);
	}
	
	private void notifyAllObservers() {
		System.out.println ("(new shape, notify observers! )" );
		int size = attachedObservers.size();
		for(int i=0; i<size; i++) {
			try {
				attachedObservers.get(i).observerNotify();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
