package dispatcher;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Dispatcher {
    public static void main(String[] args) {
        Hashtable<String, String> prop = new Hashtable<String, String>();
        prop.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        prop.put("queue.print","print");
        try{
            Context jndiContext = new InitialContext(prop);
            QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            QueueConnection qc = qcf.createQueueConnection();
            qc.start();
            Queue print = (Queue) jndiContext.lookup("print");
            QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver qr = qs.createReceiver(print);
            DispatcherListener dl = new DispatcherListener();
            qr.setMessageListener(dl);
            System.out.println("[DISPATCHER] Pronto!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
