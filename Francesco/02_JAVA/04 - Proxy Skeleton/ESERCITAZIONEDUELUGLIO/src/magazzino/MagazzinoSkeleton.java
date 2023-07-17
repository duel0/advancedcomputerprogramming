package magazzino;

import java.net.ServerSocket;
import java.net.Socket;

import service.IMagazzino;

public class MagazzinoSkeleton implements IMagazzino{
    private IMagazzino magazzino;
    public MagazzinoSkeleton(IMagazzino m){
        magazzino=m;
    }

    public void runSkeleton(){
        try {
            ServerSocket ss = new ServerSocket(2500);
            Socket s = null;
            while(true){
                s = ss.accept();
                WorkerThread t = new WorkerThread(s, this);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deposita(String articolo, int id) {
        magazzino.deposita(articolo, id);
    }

    @Override
    public int preleva(String articolo) {
        return magazzino.preleva(articolo);
    }
}
