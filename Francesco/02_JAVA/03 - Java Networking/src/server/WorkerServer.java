package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WorkerServer extends Thread{
    private Socket s;

    public WorkerServer(Socket socket){
        s=socket;
    }

    public void run(){
        try{
            DataInputStream fromClient = new DataInputStream(s.getInputStream());
            DataOutputStream toClient = new DataOutputStream(s.getOutputStream());

            System.out.println("[SERVER-WORKER] Attesa stringa...");
            String st = fromClient.readUTF();
            System.out.println("[SERVER-WORKER] Stringa ricevuta: "+st);
            toClient.writeUTF("Grazie per il messaggio ciccio!");

            toClient.close();
            fromClient.close();
            s.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
