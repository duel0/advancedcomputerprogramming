package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.ICounter;

public abstract class CounterSkel implements ICounter{
    public void runSkeleton(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(2500);
            System.out.println("Server in ascolto su 2500");
            while(true){
                socket = serverSocket.accept();
                CounterWorker cw = new CounterWorker(socket,this);
                cw.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
