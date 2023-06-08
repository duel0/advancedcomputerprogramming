package service;

import java.rmi.*;

public interface Observer extends Remote{
	public void observerNotify() throws RemoteException;
}
