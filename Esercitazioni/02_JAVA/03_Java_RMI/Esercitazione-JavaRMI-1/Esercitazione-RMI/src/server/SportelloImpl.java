package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.Semaphore;

import interfaces.ISportello;


public class SportelloImpl extends UnicastRemoteObject implements ISportello{
	
	private static final long serialVersionUID = -5457541672194646702L;
	
	private Semaphore maxReqs;
	private Semaphore maxConcurrentReqs;
	
	
	protected SportelloImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		
		maxReqs = new Semaphore(5);
		maxConcurrentReqs = new Semaphore(3);
		
	}

	@Override
	public boolean serviRichiesta(int idCliente) throws RemoteException {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		if (!maxReqs.tryAcquire()) {
			
			System.out.println("[Sportello] Raggiunto limite richieste");
			System.out.println("[Sportello] Richiesta da " + idCliente + " non servita");
			return result;
		}
		
		try {
			
			maxConcurrentReqs.acquire();
			
			Random rand = new Random();
			
			Thread.sleep((rand.nextInt(5) + 1) * 1000);
			
			FileWriter fw = new FileWriter("requests.txt", true);
			BufferedWriter bw = new BufferedWriter(fw); 
			PrintWriter pw = new PrintWriter(bw); 
			
			pw.println(idCliente);
			pw.flush();
			
			pw.close();
			bw.close();
			fw.close();
			
			result = true;
			
			System.out.println("[Sportello] Servita richiesta da " + idCliente);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
			
		} finally {
			maxConcurrentReqs.release();
			maxReqs.release();
		}
		
		return result;
	}

}
