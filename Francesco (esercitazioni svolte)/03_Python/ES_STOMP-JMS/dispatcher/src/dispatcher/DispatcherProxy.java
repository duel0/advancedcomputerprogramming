package dispatcher;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.jms.*;

public class DispatcherProxy implements IDispatcher{

    private String host;
    private int port;
    private QueueSession qs;
    private Queue r;

    public DispatcherProxy(String h, int p, QueueSession qs, Queue q){
        host=h;
        port=p;
        this.qs=qs;
        r=q;
    }


    @Override
    public void deposita(int v) {
        try {
            Socket s = new Socket(host,port);
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            BufferedReader istream = new BufferedReader(new InputStreamReader(s.getInputStream()));

            ostream.writeUTF("deposita-"+v);
            System.out.println("[DISPATCEHR] Deposito "+v);

            String response = istream.readLine();

            TextMessage m = qs.createTextMessage(response+"-"+v);
            QueueSender sender = qs.createSender(r);
            sender.send(m);
            ostream.close();
            istream.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int preleva() {
        String response = null;
        try {
            Socket s = new Socket(host,port);
            DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
            BufferedReader istream = new BufferedReader(new InputStreamReader(s.getInputStream()));

            ostream.writeUTF("preleva");
            response = new String(istream.readLine());
            System.out.println("[DISPATCHER] Prelevato "+response);

            TextMessage m = qs.createTextMessage("Prelevato-"+response);
            QueueSender sender = qs.createSender(r);
            sender.send(m);
            s.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.valueOf(response);

    }

}