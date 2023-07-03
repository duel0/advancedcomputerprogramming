package pubsubapp_asynch;

import java.util.Hashtable;
import javax.jms.*;
import javax.naming.*;

public class Publisher{
    public static void main(String[] args) throws NamingException, JMSException{
        if(args.length<2) return;

        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        properties.put("topic.soccer","soccernews");
        properties.put("topic.tennis","tennisnews");
        Context jndiContext = new InitialContext(properties);

        TopicConnectionFactory tcf = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
        Topic soccer = (Topic)jndiContext.lookup("soccer");
        Topic tennis = (Topic)jndiContext.lookup("tennis");

        TopicConnection tc = tcf.createTopicConnection();
        TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic selectedTopic;
        if(args[0].equalsIgnoreCase("soccer")){
            selectedTopic=soccer;
        } else if(args[0].equalsIgnoreCase("tennis")) {
            selectedTopic=tennis;
        } else {
            System.out.println("Unknown topic");
            return;
        }

        TopicPublisher publisher = ts.createPublisher(selectedTopic);
        TextMessage message = ts.createTextMessage(args[1]);
        message.setIntProperty("propInt", 10);
        publisher.publish(message);
        System.out.println("Ho pubblicato "+args[1]);

        publisher.close();
        ts.close();
        tc.close();
    }  
}