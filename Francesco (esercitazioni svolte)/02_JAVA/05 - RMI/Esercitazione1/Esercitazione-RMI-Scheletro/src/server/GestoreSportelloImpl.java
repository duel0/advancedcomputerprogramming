package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IGestoreSportello;
import interfaces.ISportello;


public class GestoreSportelloImpl extends UnicastRemoteObject implements IGestoreSportello{

	private Vector<ISportello> sportelli;
	private static final long serialVersionUID = 1L;

	protected GestoreSportelloImpl() throws RemoteException{
		// TODO Auto-generated constructor stub
		sportelli = new Vector<ISportello>();
		
	}

	public boolean sottoponiRichiesta(int idCliente) throws RemoteException{
		// TODO Auto-generated method stub

		for(int i=0; i<sportelli.size(); i++){
			if(sportelli.get(i).serviRichiesta(idCliente)){
				return true;
			}
		}
		return false;
	}

	public void sottoscrivi(ISportello sportello) throws RemoteException{
		// TODO Auto-generated method stub
		System.out.println("Sottoscrizione di uno sportello");
		sportelli.add(sportello);
	}

}
