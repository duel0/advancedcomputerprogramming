package server;

import dispatcher.*;


public class DispatcherImpl implements IDispatcher {		// impl. per delega
	
//public class DispatcherImpl extends DispatcherSkeletonE {	// impl. per ereditarieta'

	/*
	 * TODO: completare l'implementazione 
	 * di sendCmd e getCmd
	 * tramite la coda a gestione circolare
	 * 
	 */
	private int command;  
	
	/*
	 * in caso di implementazione per erditarieta', DispatcherImpl inizializza port in DepositoSkeletonE
	 */
	//public DispatcherImpl  ( int p ){
	//	super (p); 
	//}
	
	public void sendCmd ( int cmd ){
		System.out.println ("		+ [DispImp] sendCmd: " + cmd );
		command = cmd;
	}
	
	public int getCmd(){
		System.out.println ("		+ [DispImp] getCmd: " + command );	
		return command;
	}
	

}
