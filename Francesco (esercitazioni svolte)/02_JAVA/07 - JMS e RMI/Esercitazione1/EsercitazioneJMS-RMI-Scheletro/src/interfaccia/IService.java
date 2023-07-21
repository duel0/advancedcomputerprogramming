package interfaccia;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {
	
	public void deposita(int i) throws RemoteException;
	
	public int preleva() throws RemoteException;

}
