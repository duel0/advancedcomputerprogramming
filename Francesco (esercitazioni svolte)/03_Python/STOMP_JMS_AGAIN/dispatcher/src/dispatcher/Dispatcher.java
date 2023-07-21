package dispatcher;

import javax.jms.*;
import javax.naming.*;
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
            Queue request = (Queue) jndiContext.lookup("request");
            Queue response = (Queue) jndiContext.lookup("response");
            qc.start();
            QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver qr = qs.createReceiver(request);
            MyListener l = new MyListener(qs,response);
            qr.setMessageListener(l);
            System.out.println("Dispatcher Pronto!");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
