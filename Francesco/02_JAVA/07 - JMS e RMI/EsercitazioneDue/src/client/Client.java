package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.*;


public class Client {
    public static void main(String[] args) {
        if(args.length!=1) return;
        Hashtable<String, String> prop = new Hashtable<String, String>();
        prop.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        prop.put("queue.print","print");
        try{
            Context jndiContext = new InitialContext(prop);
            QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            QueueConnection qc = qcf.createQueueConnection();
            Queue print = (Queue) jndiContext.lookup("print");
            QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueSender qsender = qs.createSender(print);
            MapMessage m = qs.createMapMessage();
            for(int i=0; i<5; i++){
                Random random = new Random();
                int randInt = random.nextInt(40);
                m.setString("documento", "doc_"+randInt);
                m.setString("printer",args[0]);
                System.out.println("[CLIENT] Invio doc_"+randInt+" a "+args[0]);
                qsender.send(m);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
