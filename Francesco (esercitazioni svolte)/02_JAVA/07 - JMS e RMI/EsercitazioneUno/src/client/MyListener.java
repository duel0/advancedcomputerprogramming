package client;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener implements MessageListener {
	
	@Override
	public void onMessage(Message arg0) {
		
		MapMessage msg = (MapMessage) arg0;
		System.out.println("[CLIENT LISTENER] Attendo messaggi...");
		try {
			System.out.println("Ricevuto messaggio dalla coda Risposta: "+msg.getInt("valore"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
