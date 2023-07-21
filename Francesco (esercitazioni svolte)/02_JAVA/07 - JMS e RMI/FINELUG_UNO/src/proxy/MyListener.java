package proxy;

import javax.jms.*;

public class MyListener implements MessageListener {

	private QueueSession qs;

	public MyListener(QueueSession s){
		qs=s;
	}
	@Override
	public void onMessage(Message arg0) {
		
		MapMessage msg = (MapMessage) arg0;
		Receiver r = new Receiver(qs, msg);
		r.start();
		
	}

}
