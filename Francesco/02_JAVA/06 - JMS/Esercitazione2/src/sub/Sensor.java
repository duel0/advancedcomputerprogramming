package sub;

import javax.naming.*;

import coda.CodaImpl;
import coda.CodaWrapperLock;

import javax.jms.*;
import java.util.Hashtable;

public class Sensor {
    public static void main(String[] args) {
        CodaImpl coda = new CodaImpl(5);
        CodaWrapperLock wrapper = new CodaWrapperLock(coda);
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        properties.put("topic.commands","commands");
        try{
            Context jndiContext = new InitialContext(properties);
            TopicConnectionFactory tcf = (TopicConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
            Topic commands = (Topic)jndiContext.lookup("commands");
            TopicConnection tc = tcf.createTopicConnection();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber subscriber = ts.createSubscriber(commands);
            SensorListener listener = new SensorListener(wrapper);
            subscriber.setMessageListener(listener);
            tc.start();
            System.out.println("Sensore in ascolto!");
            TExecutor t = new TExecutor(wrapper);
            t.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
