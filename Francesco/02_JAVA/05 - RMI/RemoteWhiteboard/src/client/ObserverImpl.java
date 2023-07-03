package client;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import whiteboard.*;

public class ObserverImpl extends UnicastRemoteObject implements Observer{
    private static final long serialVersionUID = 1L;
    private Whiteboard remoteWhiteboard;

    public ObserverImpl(Whiteboard w) throws RemoteException{
        remoteWhiteboard=w;
    }

    public void observerNotify() throws RemoteException {
        Vector<Shape> v = remoteWhiteboard.getShapes();
        int size = v.size();
        for(int i=0; i<size; i++){
            ((Shape)v.get(i)).draw();
        }
    }
}
