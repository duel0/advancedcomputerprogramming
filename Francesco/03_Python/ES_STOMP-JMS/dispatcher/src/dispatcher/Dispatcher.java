package dispatcher;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Dispatcher {
    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("Dammi il porto!");
            return;
        }
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        properties.put("queue.request","request");
        properties.put("queue.response", "response");
        try {
            Context jndiContext = new InitialContext(properties);
            QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            QueueConnection qc = qcf.createQueueConnection();
            qc.start();
            Queue request = (Queue) jndiContext.lookup("request");
            Queue response = (Queue) jndiContext.lookup("response");
            QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver qr = qs.createReceiver(request);
            DispatcherMsgListener listener = new DispatcherMsgListener(Integer.valueOf(args[0]), qs, response);
            qr.setMessageListener(listener);
            System.out.println("[DISPATCHER] Pronto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}