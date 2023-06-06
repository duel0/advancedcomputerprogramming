package client;
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args){
        try{
            Socket s = new Socket("127.0.0.1",8050);
            System.out.println("[Client]: socket creata");

            DataOutputStream toServer = new DataOutputStream(s.getOutputStream());
            DataInputStream fromServer = new DataInputStream(s.getInputStream());

            toServer.writeUTF("Ciao brother!");
            
            System.out.println("[Client] attendo risposta...");
            String res = fromServer.readUTF();
            System.out.println("Il server mi ha risposto "+res);

            toServer.close();
            fromServer.close();
            s.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
