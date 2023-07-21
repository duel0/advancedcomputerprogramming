package client;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Client {
	
	public static void main(String[] args){
		
		Hashtable<String, String> prop = new Hashtable<String, String> ();
		prop.put( "java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		prop.put( "java.naming.provider.url", "tcp://127.0.0.1:61616" );
		prop.put("queue.richiesta","richiesta");
		prop.put("queue.risposta","risposta");
		try {
			Context jndiContext = new InitialContext(prop);
			QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
			QueueConnection qc = qcf.createQueueConnection();
			qc.start();
			Queue richiesta = (Queue) jndiContext.lookup("richiesta");
			Queue risposta = (Queue) jndiContext.lookup("risposta");
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver r = qs.createReceiver(risposta);
			MyListener l = new MyListener();
			r.setMessageListener(l);
			Sender s = new Sender(qs,richiesta,risposta);
			s.start();
			System.out.println("Client pronto...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
