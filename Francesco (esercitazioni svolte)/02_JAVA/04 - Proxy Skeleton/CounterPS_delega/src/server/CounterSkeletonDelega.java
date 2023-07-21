package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.ICounter;

public class CounterSkeletonDelega implements ICounter{
    private ICounter counter;

    public CounterSkeletonDelega(CounterImpl count){
        this.counter=count;
    }

    public void runSkeleton(){
        ServerSocket serverSocket = null;
        Socket socket = null;

        try{
            serverSocket = new ServerSocket(2500);
            System.out.println("Server in ascolto su 2500");
            while(true){
                socket = serverSocket.accept();
                CounterWorker st = new CounterWorker(socket, counter);
                st.start();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void inc(){
        counter.inc();
    }

    public void sum(int i){
        counter.sum(i);
    }

    public void square(){
        counter.square();
    }

    public int get(){
        return counter.get();
    }
}
