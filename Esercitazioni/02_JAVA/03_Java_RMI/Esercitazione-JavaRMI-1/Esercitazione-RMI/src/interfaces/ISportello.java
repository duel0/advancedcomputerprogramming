package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ISportello extends Remote {
	
	boolean serviRichiesta(int idCliente) throws RemoteException;

}
