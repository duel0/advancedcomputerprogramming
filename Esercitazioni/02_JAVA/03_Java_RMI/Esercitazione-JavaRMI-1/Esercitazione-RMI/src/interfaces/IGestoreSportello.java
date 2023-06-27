package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IGestoreSportello extends Remote {
	
	public boolean sottoponiRichiesta(int idCliente) throws RemoteException;
	
	public void sottoscrivi(ISportello sportello) throws RemoteException;

}
