package proxy;

import javax.jms.*;

public class Sender extends Thread {
	private QueueSession qs;
	private MapMessage m;
	private int id;

	public Sender(QueueSession s, MapMessage m, int i){
		qs=s;
		this.m=m;
		this.id=i;
	}
	@Override
	public void run() {
		
		try {
			QueueSender s = qs.createSender((Queue) m.getJMSReplyTo());
			MapMessage ma = qs.createMapMessage();
			ma.setInt("id", id);
			s.send(ma);
			System.out.println("[SENDER] Invio: "+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
