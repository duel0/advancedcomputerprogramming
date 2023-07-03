package pub;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class ControlStation {
    public static void main(String[] args) {
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        properties.put("topic.commands","commands");
        try{
            Context jndiContext = new InitialContext(properties);
            TopicConnectionFactory topicConnFactory = (TopicConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
            Topic commands = (Topic) jndiContext.lookup("commands");
            TopicConnection topicConn = topicConnFactory.createTopicConnection();
            TopicSession topicSess = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            TopicPublisher publisher = topicSess.createPublisher(commands);
            for(int i=0; i<20; i++){
                Thread.sleep(1000);
                TextMessage m;
                int command = (int)(Math.random()*3);
                if(command==0){
                    m = topicSess.createTextMessage("startSensor");
                } else if(command==1){
                    m = topicSess.createTextMessage("stopSensor");
                } else {
                    m = topicSess.createTextMessage("read");
                }
                publisher.publish(m);
                System.out.println("[PUBLISHER] Pubblico: "+m.getText());
            }
            publisher.close();
            topicSess.close();
            topicConn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
