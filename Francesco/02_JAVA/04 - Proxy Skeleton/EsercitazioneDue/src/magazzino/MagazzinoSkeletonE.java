package magazzino;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IMagazzino;

public abstract class MagazzinoSkeletonE implements IMagazzino{
    public MagazzinoSkeletonE(){
        super();
    }

    public void runSkeleton(){
        try {
            ServerSocket server = new ServerSocket(2500);
            Socket socket = null;
            System.out.println("Avvio Server....");
            while(true){
                socket = server.accept();
                MagazzinoThread t = new MagazzinoThread(socket, this);
                t.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
