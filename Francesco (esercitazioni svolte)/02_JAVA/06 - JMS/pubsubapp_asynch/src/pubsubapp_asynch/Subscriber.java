package pubsubapp_asynch;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Subscriber {
    public static void main(String[] args) throws NamingException, JMSException{
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");	
		properties.put("topic.soccer" , "soccernews");
        Context jndiContext = new InitialContext(properties);

        TopicConnectionFactory tcf = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
        Topic soccer = (Topic) jndiContext.lookup("soccer");

        TopicConnection tc = tcf.createTopicConnection();

        TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        TopicSubscriber subscriber = ts.createSubscriber(soccer, "propInt=10", false);
        MyListener msgl = new MyListener();
        subscriber.setMessageListener(msgl);
        tc.start();
        System.out.println("Listener set....");
    }
}
