package proxy;

import javax.jms.*;
import javax.naming.*;

import java.util.Hashtable;

public class Proxy {
	public static void main(String[] args) {
		Hashtable<String, String> prop = new Hashtable<String, String> ();
		prop.put( "java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		prop.put( "java.naming.provider.url", "tcp://127.0.0.1:61616" );
		prop.put("queue.richiesta","richiesta");
		try {
			Context jndiContext = new InitialContext(prop);
			QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
			QueueConnection qc = qcf.createQueueConnection();
			qc.start();
			Queue richiesta = (Queue) jndiContext.lookup("richiesta");
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver r = qs.createReceiver(richiesta);
			MyListener l = new MyListener(qs);
			r.setMessageListener(l);
			System.out.println("[PROXY] In attesa...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
