package client;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Client {
	
	public static void main(String[] args){
		
		Hashtable<String, String> p = new Hashtable<String, String>();
		p.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
		p.put("queue.Richiesta", "Richiesta");
		p.put("queue.Risposta", "Risposta");

		try{

			Context jndiProperties = new InitialContext(p);
			QueueConnectionFactory qcf = (QueueConnectionFactory) jndiProperties.lookup("QueueConnectionFactory");
			QueueConnection qc = qcf.createQueueConnection();

			qc.start();

			Queue richiesta = (Queue) jndiProperties.lookup("Richiesta");
			Queue risposta = (Queue) jndiProperties.lookup("Risposta");

			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Sender sender = new Sender(qs,richiesta,risposta);
			sender.start();

			Receiver receiver = new Receiver(qs,risposta);
			receiver.start();

			
			System.out.println("Client pronto...");

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}