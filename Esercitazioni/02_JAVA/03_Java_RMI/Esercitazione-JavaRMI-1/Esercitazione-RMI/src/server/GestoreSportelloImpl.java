package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IGestoreSportello;
import interfaces.ISportello;


public class GestoreSportelloImpl extends UnicastRemoteObject implements IGestoreSportello {

	private static final long serialVersionUID = 2725155631126219696L;
	
	private Vector<ISportello> sportelli;
	
	protected GestoreSportelloImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		
		sportelli = new Vector<ISportello>();
		
	}

	@Override
	public boolean sottoponiRichiesta(int idCliente) throws RemoteException {
		// TODO Auto-generated method stub
		
		boolean result = false;
		int i = 0;
		
		while ((!result) && (i < sportelli.size())) {
			result = sportelli.get(i).serviRichiesta(idCliente);
			i++;
		}
		
		System.out.println("[Gestore] Richiesta da " + idCliente + " terminata con esito " + result);
		
		return result;
	}

	@Override
	public void sottoscrivi(ISportello sportello) throws RemoteException {
		// TODO Auto-generated method stub
		
		sportelli.add(sportello);
		
		System.out.println("[Gestore] Ricevuta nuova sottoscrizione");
		
	}

}
