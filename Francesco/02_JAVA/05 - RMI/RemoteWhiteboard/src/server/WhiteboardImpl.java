package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import whiteboard.*;

public class WhiteboardImpl extends UnicastRemoteObject implements Whiteboard{

	private int count;
	private Vector<Shape> boardContent;
	private Vector<Observer> attachedObservers;
	
	public WhiteboardImpl() throws RemoteException {
		super();
		count=0;
		boardContent = new Vector<Shape>();
		attachedObservers = new Vector<Observer>();
	}
	
	public void addShape(Shape s) throws RemoteException {
		count = count+1;
		System.out.println ("\nAdding the shape #" + count + " " + s.toString() );
		s.draw();
		boardContent.add(s);
		notifyAllObservers();
	}
	
	public Vector<Shape> getShapes() throws RemoteException{
		return boardContent;
	}
	
	public void attachObserver(Observer observer) throws RemoteException{
		System.out.println ("\nNew observer attached! \n " + observer.toString() );
		attachedObservers.add(observer);
	}
	
	private void notifyAllObservers() { //Sempre private!
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
