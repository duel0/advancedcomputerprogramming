package dispatcher;

import javax.naming.*;
import javax.jms.*;
import java.util.Hashtable;

public class Dispatcher {
    public static void main(String[] args) {
        Hashtable<String, String> p = new Hashtable<String, String>();
        p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        p.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        p.put("queue.request","request");
        p.put("queue.response","response");
        try {
            Context jndiContext = new InitialContext(p);
            QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            QueueConnection qc = qcf.createQueueConnection();
            qc.start();
            Queue request = (Queue) jndiContext.lookup("request");
            Queue response = (Queue) jndiContext.lookup("response");
            QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            int port = 2500;
            DispatcherMsgListener listener = new DispatcherMsgListener(qs, response, port);
            QueueReceiver qr = qs.createReceiver(request);
            qr.setMessageListener(listener);
            System.out.println("Dispatcher avviato!");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
