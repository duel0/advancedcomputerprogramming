package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        try{
            Socket s = new Socket("127.0.0.1", 8050);
            System.out.println("[CLIENT] Socket creata");

            DataInputStream fromServer = new DataInputStream(s.getInputStream());
            DataOutputStream toServer = new DataOutputStream(s.getOutputStream());

            toServer.writeUTF("Ciao ciccio sono il client!");
            System.out.println("[CLIENT] Ho salutato il server");
            String resp = fromServer.readUTF();
            System.out.println("[CLIENT] Il server mi ha detto: "+resp);

            toServer.close();
            fromServer.close();
            s.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
