package proxy;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;

import interfaccia.IService;


public class MyListener implements MessageListener {
	private IService s;
	private QueueConnection qc;

	public MyListener(QueueConnection qc, IService s){
		this.qc=qc;
		this.s=s;
	}
	@Override
	public void onMessage(Message arg0) {
		
		MapMessage m = (MapMessage) arg0;
		System.out.println("[LISTENER - PROXY] Attendo...");

		try{
			if(m.getString("operazione").equals("deposita")){
				s.deposita(m.getInt("valore"));
				System.out.println("Depositato: "+m.getInt("valore"));
			} else {
				Sender sender = new Sender(qc,s,m);
				sender.start();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
