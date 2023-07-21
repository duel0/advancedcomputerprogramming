package client;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.IMagazzino;

public class MagazzinoProxy implements IMagazzino{

    private String HOST;
    private int PORT;

    public MagazzinoProxy(String Host, int Port){
        this.HOST=Host;
        this.PORT=Port;
    }

    @Override
    public void deposita(String articolo, int id) {
        try {
            Socket s = new Socket(HOST, PORT);

            DataOutputStream ostream = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            ostream.writeUTF("deposita");
            ostream.writeUTF(articolo);
            ostream.writeInt(id);
            ostream.flush();
            s.close();
            ostream.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int preleva(String articolo) {
        int x = 0;
        try {
            Socket s = new Socket(HOST, PORT);

            DataOutputStream ostream = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            DataInputStream istream = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            ostream.writeUTF("preleva");
            ostream.writeUTF(articolo);
            ostream.flush();
            x = istream.readInt();
            System.out.println("STO A PRELEVAAA "+x);
            s.close();
            ostream.close();
            istream.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return x;
    }
    
}
