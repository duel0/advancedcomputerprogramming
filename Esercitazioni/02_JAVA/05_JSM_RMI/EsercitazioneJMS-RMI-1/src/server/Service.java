package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaccia.IService;

public class Service extends UnicastRemoteObject implements IService {
	
	private static final long serialVersionUID = 851495907233092267L;
	
	private int coda[];
	private int putptr,takeptr,count;
	
	private Lock lock;
	private final Condition nonPieno;
	private final Condition nonVuoto;
	
	protected Service() throws RemoteException {
		super();
		coda = new int[5];
		putptr = 0;
		takeptr = 0;
		count = 0;
		lock = new ReentrantLock();
		nonPieno = lock.newCondition();
		nonVuoto = lock.newCondition();
		
	}

	@Override
	public void deposita(int i) {
		
		lock.lock();
		
		try{
			
			while(isPieno()) 
				nonPieno.await();
			
			coda[putptr] = i;
			putptr = (putptr + 1) % coda.length;
			count++;
		
			nonVuoto.signal();
			
			System.out.println("[SERVICE] Depositato " + i);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}

	@Override
	public int preleva() {
		
		lock.lock();
		
		try{
			
			while(isVuoto()) 
				nonVuoto.await();
			
			int valorePrelevato = coda[takeptr];
			takeptr = (takeptr + 1) % coda.length;
			count--;
			
			nonPieno.signal();
			
			System.out.println("[SERVICE] Prelevato " + valorePrelevato);
			
			return valorePrelevato;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return 0;
	}
	
	private boolean isPieno(){
		return count == coda.length;
	}
	
	private boolean isVuoto(){
		return count == 0;
	}

}
