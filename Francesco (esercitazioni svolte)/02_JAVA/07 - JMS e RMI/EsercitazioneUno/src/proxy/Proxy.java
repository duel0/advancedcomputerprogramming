package proxy;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import interfaccia.IService;

public class Proxy {
	
	
	public static void main(String[] args) {
		Hashtable<String, String> p = new Hashtable<String, String>();
		p.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
		p.put("queue.Richiesta", "Richiesta");
		p.put("queue.Risposta", "Risposta");

		try {
			Context jndiContext = new InitialContext(p);
			QueueConnectionFactory qcf = (QueueConnectionFactory)jndiContext.lookup("QueueConnectionFactory");
			Queue richiesta = (Queue)jndiContext.lookup("Richiesta");
			QueueConnection qc = qcf.createQueueConnection();
			qc.start();
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IService service = (IService) rmiRegistry.lookup("Service");

			Receiver receiver = new Receiver(qc,richiesta,service);
			receiver.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
