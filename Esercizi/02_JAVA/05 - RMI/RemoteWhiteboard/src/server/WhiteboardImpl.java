package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

import whiteboard.*;

public class WhiteboardImpl extends UnicastRemoteObject implements Whiteboard {
	
	
	private int count;
	private Vector<Shape> boardContent;			//contiene le shapes disegnate sulla lavagna elettronica
	private Vector<Observer> attachedObservers;	//contiene gli observer registrati
	
	protected final static long serialVersionUID = 10;
	
	public WhiteboardImpl () throws RemoteException {
		count =0;
		boardContent = new Vector<Shape>();
		attachedObservers = new Vector<Observer> ();
	}
	
	/*
	 * 
	 * TODO (esercizio): gestire la mutua esclusione su addShape
	 * 
	 */
	
	public void addShape (	Shape s ) throws RemoteException {
	
		count = count +1;
		System.out.println ("\nAdding the shape #" + count + " " + s.toString() );
		
		s.draw();
		
		boardContent.add(s);
		
		notifyAllObservers();
		
	}
	
	
	public Vector<Shape> getShapes () throws RemoteException {
		return boardContent;
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
