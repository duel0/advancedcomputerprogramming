package client;

public class ClientB {
    public static void main(String[] args) {
        MagazzinoProxy m = new MagazzinoProxy("127.0.0.1", 2500);
        ClientThread[] threads = new ClientThread[5];

        for(int i=0; i<5; i++){
            ClientThread thread = new ClientThread(m, "preleva");
            thread.start();
            threads[i]=thread;
        }

        for(int i=0; i<5; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
