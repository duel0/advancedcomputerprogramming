package manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.IRider;

public class RiderProxy implements IRider{

    private int location;
    private int port;

    public RiderProxy(int l, int p){
        location=l;
        port=p;
    }

    @Override
    public int getLocation() {
        return location;
    }

    @Override
    public void notifyOrder(int o, String add) {
        try {

            // Socket TCP, non attendo risposte, invio con DataOutputStream e alla fine faccio il flush del buffer

            Socket s = new Socket("127.0.0.1", port);
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            ostream.writeInt(o);
            ostream.writeUTF(add);
            ostream.flush();
            System.out.println("[MANAGER] Ordine "+o+" inviato su socket!");
            s.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
