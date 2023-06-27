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

			if(messagge.equalsIgnoreCase("get_mean")){
			
				// invoca il DispatcherProxy che deve invocare il servizio di prelievo
				System.out.println("[DispatcherMsgListener] Ricevuta richiesta di get_mean");
				
				dispatcher.get_mean();
				
			}
			else{
				// Deposito
				String[] splitted = messagge.split("-");
				Integer year_to_predict = Integer.valueOf(splitted[1]);
				System.out.println("[DispatcherMsgListener] Ricevuta richiesta di forecast per l'anno " + year_to_predict );
				
				// invoca il DispatcherProxy che deve invocare il servizio di deposito
				dispatcher.forecast(year_to_predict);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
