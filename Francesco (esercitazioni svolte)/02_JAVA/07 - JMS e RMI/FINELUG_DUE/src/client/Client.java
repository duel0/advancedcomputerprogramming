package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.*;

public class Client {
    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("Dimmi la printer!");
            return;
        }
        Hashtable<String, String> p = new Hashtable<String, String>();
        p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        p.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        p.put("topic.PrintRequest","PrintRequest");
        try {
            Context jndiContext = new InitialContext(p);
            TopicConnectionFactory tcf = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            TopicConnection tc = tcf.createTopicConnection();
            tc.start();
            Topic printTopic = (Topic) jndiContext.lookup("PrintRequest");
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher pub = ts.createPublisher(printTopic);
            for(int i=0; i<5; i++){
                MapMessage m = ts.createMapMessage();
                m.setString("printer", args[0]);
                Random random = new Random();
                int randint = random.nextInt(40);
                m.setString("documento", "doc_"+randint);
                System.out.println("[CLIENT] Invio "+m.getString("documento"));
                pub.send(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
