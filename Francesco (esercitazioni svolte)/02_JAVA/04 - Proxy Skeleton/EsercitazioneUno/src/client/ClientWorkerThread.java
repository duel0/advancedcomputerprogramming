package client;

import dispatcher.IDispatcher;

public class ClientWorkerThread extends Thread{
    
    private IDispatcher dp;
    public ClientWorkerThread(IDispatcher dp){
        this.dp = dp;
    }

    public void run(){

        for(int i=0; i<3; i++){
            int x = (int)(Math.random()*4);
            
            System.out.println ("[CLN] invio comando # " + x );
            
            dp.sendCmd(x);
        }
    }
}
