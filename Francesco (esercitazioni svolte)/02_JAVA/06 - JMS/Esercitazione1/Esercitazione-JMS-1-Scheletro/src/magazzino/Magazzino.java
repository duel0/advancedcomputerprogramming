package magazzino;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;


import coda.Coda;
import codaimpl.CodaCircolare;
import codaimpl.CodaWrapperLock;

public class Magazzino {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Coda coda = new CodaCircolare(10);
		CodaWrapperLock wrapper = new CodaWrapperLock(coda);
		Hashtable<String, String> prop = new Hashtable<String, String>();
		prop.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		prop.put("queue.richiesta","richiesta");
		prop.put("queue.risposta","risposta");

		try{
			Context jndiContext = new InitialContext(prop);
			QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
			QueueConnection qc = qcf.createQueueConnection();
			qc.start();
			Queue richiesta = (Queue) jndiContext.lookup("richiesta");
			
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueReceiver receiver = qs.createReceiver(richiesta);
			MagazzinoListener listener = new MagazzinoListener(qs, wrapper);
			receiver.setMessageListener(listener);

			

		} catch (Exception e){
			e.printStackTrace();
		}

	}

}