package client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import whiteboard.*;
public class ObserverImpl extends UnicastRemoteObject implements Observer{
	private static final long serialVersionUID = 1L;
	private Whiteboard remoteWhiteboard;
	
	public ObserverImpl(Whiteboard w) throws RemoteException{
		super();
		remoteWhiteboard=w;
	}
	
	public void observerNotify() throws RemoteException{
		System.out.println ("\n\nNotified! Current board content: ");
		Vector<Shape> v = remoteWhiteboard.getShapes();
		int size = v.size();
		
		for(int i=0; i<size; i++) {
			((Shape) v.get(i)).draw();
		}
	}
}
