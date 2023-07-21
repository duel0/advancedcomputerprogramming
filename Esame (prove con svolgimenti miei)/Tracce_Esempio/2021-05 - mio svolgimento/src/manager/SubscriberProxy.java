package manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.ISubscriber;

public class SubscriberProxy implements ISubscriber{

    private int componentID;
    private int port;

    public SubscriberProxy(int componentID, int port){
        this.componentID=componentID;
        this.port=port;
    }

    

    public int getComponentID() {
        return componentID;
    }



    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }



    public int getPort() {
        return port;
    }



    public void setPort(int port) {
        this.port = port;
    }



    @Override
    public void notifyAlert(int criticality) {
        try {
            Socket s = new Socket("127.0.0.1", port);
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            ostream.writeInt(criticality);
            s.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
