package magazzino;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

import coda.Coda;

public class MagazzinoThread extends Thread {

	private MapMessage message;
	private Coda c;
	private QueueSession s;
	
	public MagazzinoThread (MapMessage m, Coda c, QueueSession s){
			message=m;
			this.c=c;
			this.s=s;
	}
	
	public void run (){
		try {
			if(message.getString("richiesta").equals("deposita")){
				System.out.println("[MAGAZZINO-THREAD] Deposito "+message.getInt("articolo"));
				c.inserisci(message.getInt("articolo"));
			} else {
				QueueSender qs = s.createSender((Queue) message.getJMSReplyTo());
				MapMessage reply = s.createMapMessage();
				reply.setString("richiesta", message.getString("richiesta"));
				int x = c.preleva();
				System.out.println("[MAGAZZINO-THREAD] Prelevo "+x);
				reply.setInt("articolo", x);
				qs.send(reply);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}