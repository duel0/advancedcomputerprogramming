package dispatcher;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Dispatcher {
    public static void main(String[] args) {
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
            TopicSubscriber sub = ts.createSubscriber(printTopic);
            MyListener listener = new MyListener();
            sub.setMessageListener(listener);
            System.out.println("[DISPATCHER] Avviato...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
