package client;

import java.util.Random;

import javax.jms.*;

public class Sender extends Thread {
	private QueueSession qs;
	private Queue q;
	private Queue r;
	public Sender(QueueSession qs, Queue q, Queue r){
		this.qs=qs;
		this.q=q;
		this.r=r;
	}

	public void run() {

		try {
			QueueSender sender = qs.createSender(q);
			MapMessage message = qs.createMapMessage();
			for(int i=0; i<10; i++){

			if(Math.random() < 0.5){
				
				//CASO DEPOSITA
				message.setString("operazione", "deposita");
				Random ra = new Random();
				int randomValue = ra.nextInt(100);
				message.setInt("valore", randomValue);
				
				message.setJMSReplyTo(r);

				
				sender.send(message);
				System.out.println("[CLIENT] Mandato messaggio deposita con valore: " + randomValue);
			} else{
				//CASO PRELEVA
				message.setString("operazione", "preleva");
				
				message.setJMSReplyTo(r);
				
				sender.send(message);
				System.out.println("[CLIENT] Mandato messaggio preleva");
			}
		}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
		
	}

}
