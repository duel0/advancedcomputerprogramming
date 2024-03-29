import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Dispatcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * Il dispatcher crea una destination di coda richieste per parlare con un client Python
		 * Una volta ricevuta la richiesta (asincrona tramite MessageListener) di operazione parlerà tramite Proxy-Skeleton (socket)
		 * verso il server
		 */

		Hashtable<String, String> properties = new Hashtable<String,String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("queue.request", "request");
		properties.put("queue.response", "response");
		
		try {
			
			Context jndiContext = new InitialContext(properties);
			QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
			
			// creo la coda di richieste
			Queue qrequest = (Queue)jndiContext.lookup("request");
			QueueConnection qc = qcf.createQueueConnection();
			qc.start();
			
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = qs.createReceiver(qrequest);
			
			// creo la coda di risposte
			Queue qresponse = (Queue)jndiContext.lookup("response");

			// passo al listener la sessione, la coda di risposte ed il numero di porto (ricevuto da terminale) in modo da inviare il messaggio al server
			int port = Integer.valueOf(2500);		
			// questa soluzione passaggio con la session va bene perchè non è il multithread il proxy
			DispatcherMsgListener listener = new DispatcherMsgListener(qs, qresponse, port);
			receiver.setMessageListener(listener);
			
			System.out.println("Dispatcher avviato");

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
