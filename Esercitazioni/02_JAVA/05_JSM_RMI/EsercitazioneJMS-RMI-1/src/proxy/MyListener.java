package proxy;

import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import interfaccia.IService;

public class MyListener implements MessageListener {
	
	private IService servizio;
	
	public MyListener(IService servizio) {
		
		this.servizio = servizio;
		
	}

	@Override
	public void onMessage(Message arg0) {
		
		TextMessage msg = (TextMessage)arg0;
		
		try {
			String messagge = msg.getText();
			
			if(messagge.equalsIgnoreCase("Preleva")){
				// Prelievo
				System.out.println("[LISTENER] Ricevuta richiesta prelievo");
				
				int valorePrelevato = servizio.preleva();
				Thread t = new Thread(new Sender("Valore prelevato " + valorePrelevato));
				t.start();
			}
			else{
				// Deposito
				String[] splitted = messagge.split("-");
				Integer valoreDaDepositare = Integer.valueOf(splitted[1]);
				System.out.println("[LISTENER] Ricevuta richiesta di deposito. Valore da depositare " + valoreDaDepositare );
				
				servizio.deposita(valoreDaDepositare);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
