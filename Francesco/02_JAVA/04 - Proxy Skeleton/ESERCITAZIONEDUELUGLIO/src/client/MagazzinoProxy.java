package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import service.IMagazzino;

public class MagazzinoProxy implements IMagazzino{

    @Override
    public void deposita(String articolo, int id) {
        try {
            Socket s = new Socket("127.0.0.1", 2500);
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            ostream.writeUTF("deposita");
            ostream.writeUTF(articolo);
            ostream.writeInt(id);
            ostream.flush();
            //System.out.println("[MAGAZZINO PROXY] Deposito "+articolo+" con id "+id);
            s.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public int preleva(String articolo) {
        Integer id = null;
        try {
            Socket s = new Socket("127.0.0.1", 2500);
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            DataInputStream istream = new DataInputStream(s.getInputStream());
            ostream.writeUTF("preleva");
            ostream.writeUTF(articolo);
            id = new Integer(istream.readInt());
            //System.out.println("[MAGAZZINO PROXY] Prelevo "+articolo+" con id "+id);
            s.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return id;
    }
    
}
