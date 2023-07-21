package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import whiteboard.*;

public class WhiteboardImpl extends UnicastRemoteObject implements Whiteboard{

    private int count;
    private Vector<Shape> boardContent;
    private Vector<Observer> attachedObservers;
    private static final long serialVersionUID=10;

    public WhiteboardImpl() throws RemoteException{
        count=0;
        boardContent = new Vector<Shape>();
        attachedObservers = new Vector<Observer>();
    }


    public void addShape(Shape s) throws RemoteException {
        count=count+1;
        System.out.println("Adding the shape #"+count+" "+s.toString());
        boardContent.add(s);
        notifyAllObservers();
        
    }

    public void attachObserver(Observer observer) throws RemoteException {
        System.out.println("New observer!\n");
        attachedObservers.add(observer);
    }

    public Vector<Shape> getShapes() throws RemoteException {
        return boardContent;
    }

    private void notifyAllObservers(){
        System.out.println("New shape, notify all!");
        int size = attachedObservers.size();
        for(int i=0; i<size; i++){
            try{
                attachedObservers.get(i).observerNotify();
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }
    
}
