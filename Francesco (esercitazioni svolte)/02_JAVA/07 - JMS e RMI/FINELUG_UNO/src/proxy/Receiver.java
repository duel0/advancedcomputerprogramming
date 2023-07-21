package proxy;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.MapMessage;
import javax.jms.QueueSession;

import interfaccia.IService;

public class Receiver extends Thread {
	private QueueSession qs;
	private MapMessage m;

	public Receiver(QueueSession s, MapMessage m){
		qs=s;
		this.m=m;
	}
	@Override
	public void run() {
		try {
			String op = m.getString("op");
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IService stub = (IService) rmiRegistry.lookup("service");
			if(op.equalsIgnoreCase("deposita")){
				
				int val = m.getInt("id");
				System.out.println("[RECEIVER] Deposito: "+val);
				stub.deposita(val);
			} else {
				int val = stub.preleva();
				Sender s = new Sender(qs,m,val);
				s.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
