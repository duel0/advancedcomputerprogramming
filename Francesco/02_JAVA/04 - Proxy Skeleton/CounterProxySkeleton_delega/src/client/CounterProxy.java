package client;

import service.ICounter;
import java.io.*;
import java.net.*;

public class CounterProxy implements ICounter{

    private String host;
    private int port;

    public CounterProxy(String host, int port){
        this.host=host;
        this.port=port;
    }

    public int get(){
        try{
            Socket soc = new Socket(host,port);
            DataOutputStream ostream = new DataOutputStream(new BufferedOutputStream(soc.getOutputStream()));
            DataInputStream istream = new DataInputStream(new BufferedInputStream(soc.getInputStream()));

            // marshalling
            ostream.writeUTF("get");
            ostream.flush();

            int x = istream.readInt();
            soc.close();

            return x;
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void inc(){
        try{
            Socket soc = new Socket(host, port);
            DataOutputStream ostream = new DataOutputStream(new BufferedOutputStream(soc.getOutputStream()));
            ostream.writeUTF("inc");
            ostream.flush();
            soc.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void square(){
        try{
            Socket soc = new Socket(host, port);
            DataOutputStream ostream = new DataOutputStream(new BufferedOutputStream(soc.getOutputStream()));

            ostream.writeUTF("sqr");
            ostream.flush();
            soc.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sum(int value){
        try {
            Socket soc = new Socket(host,port);
            DataOutputStream ostream = new DataOutputStream(new BufferedOutputStream(soc.getOutputStream()));

            ostream.writeUTF("sum");
            ostream.writeInt(value);

            ostream.flush();

            soc.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
