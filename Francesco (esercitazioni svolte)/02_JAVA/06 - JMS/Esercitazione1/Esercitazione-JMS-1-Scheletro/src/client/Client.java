package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.*;

public class Client {
	
	private static final int N = 10;
	
	public static void main(String[] args) {
		
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
			Queue risposta = (Queue) jndiContext.lookup("risposta");
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueReceiver receiver = qs.createReceiver(risposta);
			ClientListener listener = new ClientListener();
			receiver.setMessageListener(listener);

			QueueSender sender = qs.createSender(richiesta);

			MapMessage msg = qs.createMapMessage();

			for(int i=0; i<N; i++){
				Random random = new Random();
				int id = random.nextInt(100);
				
				if(i%2==0){
					System.out.println("[CLIENT] Deposito "+id);
					msg.setString("richiesta", "deposita");
					msg.setInt("articolo", id);
				} else {
					System.out.println("[CLIENT] Prelevo!");
					msg.setString("richiesta","preleva");
				}
				msg.setJMSReplyTo(risposta);
				sender.send(msg);
			}
			qc.close();

		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}
