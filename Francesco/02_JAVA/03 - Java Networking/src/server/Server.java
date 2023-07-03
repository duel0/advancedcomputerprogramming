package server;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(8050);
            System.out.println("[SERVER] in attesa su 8050");

            while(true){
                Socket s = server.accept();
                System.out.println("[SERVER] nuovo client!");

                WorkerServer w = new WorkerServer(s);
                w.start();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}