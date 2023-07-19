package subscriber;

import java.io.DataInputStream;
import java.net.Socket;

import service.ISubscriber;

public class SubscriberThread extends Thread{
    private Socket s;
    private ISubscriber sub;

    public SubscriberThread(Socket s, ISubscriber sub){
        this.s=s;
        this.sub=sub;
    }
    public void run(){
        try {
            DataInputStream istream = new DataInputStream(s.getInputStream());
            int crit = istream.readInt();
            sub.notifyAlert(crit);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
