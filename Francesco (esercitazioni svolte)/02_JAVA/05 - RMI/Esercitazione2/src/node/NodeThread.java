package node;

import java.rmi.RemoteException;
import java.util.Random;

import service.IDispatcher;

public class NodeThread extends Thread{
    IDispatcher dispatcher;

    public NodeThread(IDispatcher d){
        dispatcher=d;
    }

    public void run(){
        for(int i=0; i<3; i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Random num = new Random();
            int numero = num.nextInt(50);
            String docname = "doc"+numero;
            try {
                if(dispatcher.printRequest(docname)){
                    System.out.println("Stampato "+docname);
                } else {
                    System.out.println("Non stampato!");
                }
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
