package rider;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import service.IRider;

public class RiderThread extends Thread{
    private Socket s;
    private IRider r;

    public RiderThread(Socket s, IRider r){
        this.s=s;
        this.r=r;
    }

    public void run(){
        try {
            // Leggo dal buffer su socket un valore intero e una stringa
            DataInputStream istream = new DataInputStream(s.getInputStream());
            int ordine = istream.readInt();
            String indirizzo = istream.readUTF();

            // Con tali parametri, upcall all'implementazione RiderImpl
            r.notifyOrder(ordine, indirizzo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
