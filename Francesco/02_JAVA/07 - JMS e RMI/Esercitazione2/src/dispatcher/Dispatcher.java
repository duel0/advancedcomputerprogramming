package dispatcher;

import java.util.Hashtable;


import javax.jms.*;
import javax.naming.*;

public class Dispatcher {
    public static void main(String[] args) {
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
            TopicSubscriber subscriber = ts.createSubscriber(print);
            MyListener listener = new MyListener();
            subscriber.setMessageListener(listener);
            tc.start();
            System.out.println("Listener in ascolto...");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
