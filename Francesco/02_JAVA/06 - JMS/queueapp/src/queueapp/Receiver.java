package queueapp;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Receiver {
    public static void main(String[] args) {
        Hashtable<String, String> prop = new Hashtable<String, String> ();
        prop.put( "java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		prop.put( "java.naming.provider.url", "tcp://127.0.0.1:61616" );
		
		//		jndi queue name   queue-name
		prop.put( "queue.test", "mytestqueue" );

        try {  

            Context jndiContext = new InitialContext(prop);
            QueueConnectionFactory queueConnFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            Queue queue = (Queue) jndiContext.lookup("test");

            QueueConnection queueConn = (QueueConnection) queueConnFactory.createQueueConnection();
            queueConn.start();
            QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver receiver = queueSession.createReceiver(queue);

            TextMessage message;

            do{
                System.out.println("In attesa di messaggi...");
                message = (TextMessage) receiver.receive();
                System.out.println("Ricevuto: "+message.getText());
            } while(message.getText().compareTo("fine")!=0);

            receiver.close();
            queueSession.close();
            queueConn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
