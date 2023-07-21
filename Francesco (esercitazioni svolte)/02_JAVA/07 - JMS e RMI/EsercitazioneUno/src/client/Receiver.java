package client;

import javax.jms.*;

public class Receiver extends Thread {
	private QueueSession s;
	private Queue q;


	public Receiver(QueueSession s, Queue risposta){
		this.s=s;
		this.q=risposta;
	}
	
	public void run() {
		
		try {
			QueueReceiver receiver = s.createReceiver(q);
			MyListener listener = new MyListener();
			receiver.setMessageListener(listener);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
