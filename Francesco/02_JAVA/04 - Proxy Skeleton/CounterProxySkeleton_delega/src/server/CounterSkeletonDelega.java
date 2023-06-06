package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.*;

public class CounterSkeletonDelega implements ICounter {
    private ICounter counter;

    public CounterSkeletonDelega(CounterImpl count){
        counter=count;
    }

    public void runSkeleton(){
        ServerSocket serverSocket = null;
        Socket socket = null;

        try{

            serverSocket = new ServerSocket(2500);
            System.out.println("Server in ascolto su 2500");
            while(true){
                socket = serverSocket.accept();
                CounterWorker st = new CounterWorker(socket,this);
                st.start();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void inc(){
        counter.inc();
    }

    public void sum(int value){
        counter.sum(value);
    }

    public void square(){
        counter.square();
    }

    public int get(){
        return counter.get();
    }

}