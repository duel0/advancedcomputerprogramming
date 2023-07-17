package client;

public class ClientB {
    public static void main(String[] args) {
        Thread[] threads=new ThreadB[5];
        for(int i=0; i<5; i++){
            threads[i] = new ThreadB();
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
