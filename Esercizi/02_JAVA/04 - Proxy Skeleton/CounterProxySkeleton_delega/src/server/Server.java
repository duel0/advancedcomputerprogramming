package server;

public class Server { 
	
	public static void main(String[] args) {
    		
		CounterImpl count = new CounterImpl();

		/* 
		 * NOTA: Nel caso di Skeleton con delega non possiamo fare il run delle Skeleton tramite
		 * count.runSkeleton() ma deleghiamo alla classe Skeleton
		 */ 
		
		CounterSkeletonDelega skelDelega = new CounterSkeletonDelega(count);
		
		skelDelega.runSkeleton();
		
	}
	
}