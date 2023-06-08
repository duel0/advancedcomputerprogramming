package whiteboard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Whiteboard extends Remote{
	public void addShape(Shape s) throws RemoteException;
	public void attachObserver(Observer observer) throws RemoteException;
	public Vector<Shape> getShapes() throws RemoteException;
}
