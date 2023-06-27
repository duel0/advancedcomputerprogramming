import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

public class DispatcherMsgListener implements MessageListener {
	
	private QueueSession qsession;
	private Queue qresponse;
	private int port;

	public DispatcherMsgListener(QueueSession qsession, Queue qresponse, int port){

		this.qsession = qsession;
		this.qresponse = qresponse;
		this.port = port;
	}

	@Override
	public void onMessage(Message arg0) {
		
		TextMessage msg = (TextMessage)arg0;
		
		try {
			String messagge = msg.getText();
			IDispatcher dispatcher = new DispatcherProxy ("localhost", port, qsession, qresponse);

			if(messagge.equalsIgnoreCase("Preleva")){
			
				// invoca il DispatcherProxy che deve invocare il servizio di prelievo
				System.out.println("[LISTENER] Ricevuta richiesta prelievo");
				
				dispatcher.preleva();
				
			}
			else{
				// Deposito
				String[] splitted = messagge.split("-");
				Integer valoreDaDepositare = Integer.valueOf(splitted[1]);
				System.out.println("[LISTENER] Ricevuta richiesta di deposito. Valore da depositare " + valoreDaDepositare );
				
				// invoca il DispatcherProxy che deve invocare il servizio di deposito
				dispatcher.deposita(valoreDaDepositare);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
