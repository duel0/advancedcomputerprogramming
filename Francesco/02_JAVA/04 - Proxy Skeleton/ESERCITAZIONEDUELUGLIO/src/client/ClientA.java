package client;

public class ClientA {
    public static void main(String[] args) {
        Thread[] threads=new ThreadA[5];
        for(int i=0; i<5; i++){
            threads[i] = new ThreadA();
            threads[i].start();
        }
        for(int i=0; i<5; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
