package pubsubapp;

import javax.naming.*;
import javax.jms.*;

import java.util.Hashtable;

public class Subscriber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		try{
			
			Context jndiContext = new InitialContext ( jndiProperties);
		
			
			TopicConnectionFactory connFactory = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
			Topic topic = (Topic)jndiContext.lookup("test");
			
			
			TopicConnection topicConn = connFactory.createTopicConnection();

			// prima di avviare la connessione, posso stabilire se il subscriber può essere durable impostando un ID
			topicConn.setClientID("clientDurable");

			topicConn.start();
				
			TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			// se volessi creare un sub durable
			TopicSubscriber sub = topicSession.createDurableSubscriber(topic, "DurableSub");

			// sub non-durable (di default)
			//TopicSubscriber sub = topicSession.createSubscriber(topic);


			TextMessage msg;
			
			   
            do{
            	System.out.println ("In attesa di messaggi!");
            	msg = (TextMessage)sub.receive();
            	System.out.println ("	+ messaggio ricevuto: " + msg.getText());
            }while (msg.getText().compareTo("fine") != 0); 
			
			
			
			sub.close();
			topicSession.close();
			topicConn.close();
			
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		
	}

}