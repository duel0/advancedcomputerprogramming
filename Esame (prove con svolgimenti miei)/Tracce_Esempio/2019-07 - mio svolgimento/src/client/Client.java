package client;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Client {
    public static void main(String[] args) {
        try {
            if(args.length!=2){
                System.out.println("Dammi i parametri!");
                return;
            } else if (Integer.parseInt(args[0])<0 || Integer.parseInt(args[0])>100) {
                System.out.println("Dato out of bounds!");
                return;
            }
            Hashtable<String, String> p = new Hashtable<String, String>();
            p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            p.put("java.naming.provider.url","tcp://127.0.0.1:61616");
            p.put("topic.storage","storage");

            Context jndiContext = new InitialContext(p);
            TopicConnectionFactory tcf = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            TopicConnection tc = tcf.createTopicConnection();
            //tc.start();
            Topic storage = (Topic) jndiContext.lookup("storage");
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            MapMessage mess = ts.createMapMessage();
            mess.setInt("dato", Integer.parseInt(args[0]));
            mess.setInt("porta", Integer.parseInt(args[1]));
            TopicPublisher tp = ts.createPublisher(storage);
            tp.send(mess);
            System.out.println("[CLIENT] Messaggio inviato!");
            tc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
