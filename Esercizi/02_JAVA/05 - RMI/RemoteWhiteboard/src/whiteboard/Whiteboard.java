package whiteboard;

import java.rmi.*;
import java.util.*;

/*
 * interface Whiteboard, metodi della lavagna elettronica remota
 */

public interface Whiteboard extends Remote{

	public void addShape (	Shape s ) throws RemoteException ;
	
	/*
	 * Metodi per consentire l'attach di un observer
	 * sul subject (Partecipanti del design Pattern 'Observer'); 
	 * getShapes: ottiene lo stato del subject
	 */
	public void attachObserver ( Observer observer) throws RemoteException;
	
	/*
	 * getShapes() ritorna un vector di Shape. Tale vector viene popolato
	 * ogni qual volta si invoca addShape(...)
	 */
	public Vector<Shape> getShapes () throws RemoteException ;
	
}
