package magazzino;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import service.IMagazzino;

public class WorkerThread extends Thread{
    private Socket s;
    private IMagazzino m;

    public WorkerThread(Socket s, IMagazzino m){
        this.s=s;
        this.m=m;
    }

    public void run(){
        try {
            DataInputStream istream = new DataInputStream(s.getInputStream());
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            String op = istream.readUTF();
            if(op.equalsIgnoreCase("deposita")){
                String art = istream.readUTF();
                int id = istream.readInt();
                System.out.println("[MAGAZZINO THREAD] Deposito: "+art+" con id: "+id);
                m.deposita(art, id);
            } else {
                String art = istream.readUTF();
                int id = m.preleva(art);
                System.out.println("[MAGAZZINO THREAD] Prelevato: "+art+" con id: "+id);
                ostream.writeInt(id);
                ostream.flush();
            }
            istream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
