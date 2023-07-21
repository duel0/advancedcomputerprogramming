package client;

import java.util.Random;

import javax.jms.*;
public class Sender extends Thread {
	private QueueSession qs;
	private Queue q;
	private Queue re;

	public Sender(QueueSession s, Queue r, Queue resp){
		qs=s;
		q=r;
		re=resp;
	}
	
	public void run() {
		
		try {
			for(int i=0; i<10; i++){
				MapMessage m = qs.createMapMessage();
				m.setJMSReplyTo(re);
				if(i%2==0){
					m.setString("op", "deposita");
					Random ran = new Random();
					int val = ran.nextInt(100);
					m.setInt("id", val);
				} else {
					m.setString("op", "preleva");
				}
				QueueSender sender = qs.createSender(q);
				sender.send(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
