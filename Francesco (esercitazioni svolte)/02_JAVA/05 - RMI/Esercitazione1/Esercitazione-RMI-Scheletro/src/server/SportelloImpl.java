package server;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
import java.io.Serializable;

import interfaces.ISportello;

	

public class SportelloImpl extends UnicastRemoteObject implements ISportello{
	
	private static final long serialVersionUID = 1L;
	private Semaphore richieste;
	int numRichieste;
	
	protected SportelloImpl() throws RemoteException{
		// TODO Auto-generated constructor stub
		richieste = new Semaphore(3);
		numRichieste = 0;
	}

	public boolean serviRichiesta(int idCliente) throws RemoteException{
		// TODO Auto-generated method stub
		if(numRichieste < 3){
			try{
				richieste.acquire();
				numRichieste++;
				// scrivi idCliente su un file chiamato clienti.txt nella directory corrente
				int time = ((int) (Math.random() * 5 + 1))*1000;
				Thread.sleep(time);
				System.out.println("Servito cliente " + idCliente);
				numRichieste--;
				return true;

			} catch (InterruptedException e){
				e.printStackTrace();
			} finally{
				richieste.release();
			}
		} else {
			System.out.println("Sportello pieno, cliente " + idCliente + " non servito");
		}
		
		return false;
	}

}
