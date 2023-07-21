package proxy;

import javax.jms.*;

import interfaccia.IService;

public class Receiver extends Thread {
	
	private QueueConnection qc;
	private Queue q;
	private IService s;

	public Receiver(QueueConnection qc, Queue q, IService s){
		this.qc=qc;
		this.q=q;
		this.s=s;
	}
	@Override
	public void run() {
		
		try {
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = qs.createReceiver(q);
			MyListener listener = new MyListener(qc, s);
			receiver.setMessageListener(listener);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
	}
	
}
