package server;

import java.io.*;
import java.net.*;

public class Server{
    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(8050);
            System.out.println("[Server]: in attesa su 8050");
            while(true){
                Socket s = server.accept();
                System.out.println("[Server]: nuovo client, avvio del thread worker");
                WorkerServer w = new WorkerServer(s);
                w.start();

                server.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}