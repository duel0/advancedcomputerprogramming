package server;

import java.util.concurrent.Semaphore;

import dispatcher.*;


public class DispatcherImpl implements IDispatcher {		// impl. per delega
	
//public class DispatcherImpl extends DispatcherSkeletonE {	// impl. per ereditarieta'
	private Semaphore spazioDisp;
	private Semaphore comandiDisp;

	private int[] commands;

	private int head;
	private int tail;
	private int size;
	private int elem;

	/*
	 * TODO: completare l'implementazione 
	 * di sendCmd e getCmd
	 * tramite la coda a gestione circolare
	 * 
	 */
	//private int command;  
	
	/*
	 * in caso di implementazione per erditarieta', DispatcherImpl inizializza port in DepositoSkeletonE
	 */
	//public DispatcherImpl  ( int p ){
	//	super (p); 
	//}

	public DispatcherImpl(){
		size=5;
		commands = new int[size];
		comandiDisp = new Semaphore(0);
		spazioDisp = new Semaphore(size);
		elem=0;
		head=tail=0;
	}
	
	public void sendCmd ( int cmd ){
		System.out.println ("		+ [DispImp] sendCmd: " + cmd );
		//command = cmd;
		try {
			spazioDisp.acquire();
			try{
				synchronized(commands){
					commands[tail%size]=cmd;
					elem=elem+1;
					tail=tail+1;
				}
			} finally {
				comandiDisp.release();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int getCmd(){
		
		//return command;
		int x = -1;
		try {
			comandiDisp.acquire();
			try{
				synchronized(commands){
					x = commands[head%size];
					elem=elem-1;
					head=head+1;
				}
			} finally {
				spazioDisp.release();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println ("		+ [DispImp] getCmd: " + x );	
		return x;
	}
	

}
