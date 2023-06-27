package server;

public class DispatcherServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * avvio programma server: 	java server.DispatcherServer num_porta
		 * per es., 				java server.DispatcherServer 8000
		 */
		
		
		/*
		 * implementazione per delega
		 */
		
		
		DispatcherImpl dispatcher = new DispatcherImpl (5);
		DispatcherSkeletonD skeleton = new DispatcherSkeletonD ( dispatcher, Integer.valueOf(args[0]) );
		skeleton.runSkeleton();
		
		
		/*
		 * implementazione per ereditarieta'
		 * 
		 * NOTA: in DispatcherImpl, decommentare 
		 * 'public class DispatcherImpl extends DispatcherSkeletonE' ed il costruttore
		 * 
		 */
		
		//DispatcherImpl dispatcher = new DispatcherImpl ( Integer.valueOf(args[0]) );
		//dispatcher.runSkeleton();
		
	}

}
