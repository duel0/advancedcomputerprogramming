package magazzino;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueSession;

import coda.Coda;

public class MagazzinoListener implements MessageListener{
	private Coda coda;
	private QueueSession session;
	public MagazzinoListener(QueueSession s,Coda c){
		coda=c;
		session=s;
	}
	@Override
	public void onMessage(Message message) {
		
		MapMessage mess = (MapMessage) message;
		try {
			System.out.println("[MAGAZZINO-LISTENER] Ricevuto messaggio!");
			MagazzinoThread t = new MagazzinoThread(mess,coda,session);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
