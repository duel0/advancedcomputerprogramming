package subscriber;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.ISubscriber;

public class SubscriberSkeleton implements ISubscriber{
    private ISubscriber s;
    private int port;

    public SubscriberSkeleton(ISubscriber s, int p){
        this.s=s;
        port=p;
    }

    public void runSkeleton(){
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket s = null;
            while(true){
                s=ss.accept();
                SubscriberThread t = new SubscriberThread(s,this);
                t.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void notifyAlert(int criticality) {
        s.notifyAlert(criticality);
    }
}
