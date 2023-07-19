package generator;

public class Generator {
    
    public static void main(String[] args) {
        int NTHREADS = 3;
        Thread[] threads = new Thread[NTHREADS];
        for(int i=0; i<NTHREADS; i++){
            GeneratorThread t = new GeneratorThread();
            t.start();
            threads[i]=t;
        }

        for(int i=0; i<NTHREADS; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
