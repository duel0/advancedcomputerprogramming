package dispatcher;

import javax.jms.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;

public class DispatcherProxy implements IDispatcher{

    private String addr;
    private int port;
    private QueueSession qsession;
    private Queue qresponse;

    public DispatcherProxy(String a, int p, QueueSession qs, Queue qr){
        addr=a;
        port=p;
        qsession=qs;
        qresponse=qr;
    }

    @Override
    public void forecast(int valore) {
        try{
            Socket s = new Socket(addr, port);
            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

            dataOut.writeUTF("forecast-"+valore);
            String result = dataIn.readLine();
            s.close();
            TextMessage msg = qsession.createTextMessage(result);
            QueueSender sender = qsession.createSender(qresponse);
            sender.send(msg);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public float get_mean() {
        String result = null;
        try{
            Socket s = new Socket(addr,port);
            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

            dataOut.writeUTF("get_mean");
            result = new String(dataIn.readLine());
            s.close();
            TextMessage msg = qsession.createTextMessage(result);
            QueueSender sender = qsession.createSender(qresponse);
            sender.send(msg);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Float.valueOf(result);
    }
    
}
