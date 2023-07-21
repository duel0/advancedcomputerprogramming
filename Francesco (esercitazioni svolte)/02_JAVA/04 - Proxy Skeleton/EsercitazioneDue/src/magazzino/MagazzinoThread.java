package magazzino;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import service.IMagazzino;

public class MagazzinoThread extends Thread{
    private Socket s;
    private IMagazzino m;

    public MagazzinoThread(Socket s, IMagazzino m){
        this.s=s;
        this.m=m;
    }

    public void run(){
        try{
            DataInputStream istream = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream ostream = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            String operazione = istream.readUTF();
            if(operazione.equals("deposita")){
                String articolo = istream.readUTF();
                int id = istream.readInt();
                m.deposita(articolo, id);
            } else if (operazione.equals("preleva")){
                String articolo = istream.readUTF();
                int x = m.preleva(articolo);
                ostream.writeInt(x);
                ostream.flush();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
