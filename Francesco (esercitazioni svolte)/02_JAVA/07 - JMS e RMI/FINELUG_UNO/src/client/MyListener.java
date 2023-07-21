package client;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MapMessage;

public class MyListener implements MessageListener {
	
	@Override
	public void onMessage(Message arg0) {
		
		MapMessage m = (MapMessage) arg0;
		try {
			int id = m.getInt("id");
			Receiver r = new Receiver(id);
			r.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
