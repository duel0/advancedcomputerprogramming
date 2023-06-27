package server;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import dispatcher.*;


public class DispatcherImpl implements IDispatcher {		// impl. per delega
	
//public class DispatcherImpl extends DispatcherSkeletonE {	// impl. per ereditarieta'

	private int command = -1;  

	/*
	 * Elementi che identificano la coda circolare
	 */
	private int data[];	//gli elementi sono memorizzati in un array gestito in maniera circolare
	
	private int size;
	private int elem;
	
	private int tail;
	private int head;

	/*
	 * oggetti per la sincronizzazione tramite variabili condition
	 */
	private Lock lock;
	private Condition empty;
	private Condition full;

	/*
	 * Nel costruttore inizializziamo la coda circolare e le variabili per la sincronizzazione
	 */
	public DispatcherImpl(int s){

		size=s;
		elem=0;
		data = new int[size];
		tail=head=0;
	
		lock = new ReentrantLock();
		empty = lock.newCondition();
		full = lock.newCondition();

	}
	
	// metodi di utilita'

	private boolean full(){
		if ( elem == size )
			return true;
		return false;
	}
	
	private boolean empty(){
		if( elem == 0)
			return true;
		return false;
	}
	
	/*
	 * in caso di implementazione per erditarieta', DispatcherImpl inizializza port in DepositoSkeletonE
	 */
	//public DispatcherImpl  ( int p ){
	//	super (p); 
	//  ...
	//}
	
	public void sendCmd ( int cmd ){
		
		lock.lock();
		
		try{
		
			while ( this.full() ){
				try{
					empty.await();
				}catch ( InterruptedException e ){
					e.printStackTrace();
				}
			}
			
			data[ tail%size ] = cmd;
	
			System.out.println ("		+ [DispImp] sendCmd: " + cmd + " at position " + tail%size);

			elem=elem+1;
			tail=tail+1;

			full.signal();
			
		}finally{
			lock.unlock();
		}

	}
	
	public int getCmd(){
		
		lock.lock();
		
		try{
			
			while ( this.empty()){
				try{
					full.await();
				}catch ( InterruptedException e ){
					e.printStackTrace();
				}		
			}

			this.command = data[ head%size ];	
			System.out.println ("		+ [DispImp] getCmd: " + this.command + "at position " + head%size);
			
			elem=elem-1;
			head=head+1;

			empty.signal();
		}
		finally{
			lock.unlock();
		}
		
		return this.command;
	}
	

}
