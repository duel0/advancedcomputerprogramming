package client;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;
import java.util.Random;

public class Client {

    public static void main(String[] args) {
        if(args.length!=1) return;
        Hashtable<String, String> p = new Hashtable<String, String>();
        p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        p.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        p.put("topic.print","print");
        try{
            Context jndiContext = new InitialContext(p);
            TopicConnectionFactory tcf = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            TopicConnection tc = tcf.createTopicConnection();
            Topic print = (Topic) jndiContext.lookup("print");
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher publisher = ts.createPublisher(print);
            MapMessage mm = ts.createMapMessage();
            mm.setString("printer", args[0]);
            for(int i=0; i<5; i++){
                Random num = new Random();
                int caso = num.nextInt(40);
                mm.setString("documento", "doc"+caso);
                System.out.println("[CLIENT] Invio "+mm.getString("documento")+" a "+mm.getString("printer"));
                publisher.send(mm);
            }
            System.out.println("[CLIENT] Finito!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
