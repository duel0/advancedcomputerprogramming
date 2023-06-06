package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class WorkerServer extends Thread {
    Socket s;

    public WorkerServer(Socket skt){
        s=skt;
    }

    public void run(){
        try{
            DataInputStream fromClient = new DataInputStream(s.getInputStream());
            DataOutputStream toClient = new DataOutputStream(s.getOutputStream());
        
            System.out.println("[Server-Worker]: attendo stringa!");
            String st = fromClient.readUTF();
            System.out.println("[Server-Worker]: stringa ricevuta <"+st+">. Rispondo...");
            toClient.writeUTF("Richiesta Ricevuta!");

            fromClient.close();
            toClient.close();
            s.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
