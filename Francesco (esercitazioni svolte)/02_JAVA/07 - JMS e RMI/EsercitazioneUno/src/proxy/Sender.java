package proxy;

import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import interfaccia.IService;

public class Sender extends Thread {

	private IService s;
	private QueueConnection qc;
	private MapMessage m;

	public Sender(QueueConnection qc, IService s, MapMessage m){
		this.qc=qc;
		this.s=s;
		this.m=m;
	}

	@Override
	public void run() {
		
		try {
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueSender sender = qs.createSender((Queue) m.getJMSReplyTo());
			int x = s.preleva();
			MapMessage newMessage = qs.createMapMessage();
			newMessage.setString("operazione",m.getString("operazione"));
			newMessage.setInt("valore", x);
			sender.send(newMessage);
			System.out.println("[PROXY-SENDER] Invio: "+x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
