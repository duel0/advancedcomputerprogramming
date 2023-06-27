

import java.net.*;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import java.io.*;

public class DispatcherProxy implements IDispatcher {
	
	private String addr;
	private int port;
	private QueueSession qsession;
	private Queue qresponse;
	
	
	public DispatcherProxy ( String a, int p , QueueSession qsession, Queue qreponse){
		this.addr = new String ( a);
		this.port = p;	
		this.qsession = qsession;
		this.qresponse = qreponse;
	}
	
	
	public void deposita(int valore){
		
		try{
			Socket s = new Socket ( addr, port );
			
			DataOutputStream dataOut = new DataOutputStream ( s.getOutputStream() );
			
			// NOTE: A BufferedReader is used to receive data from a Python application, since it allows using the readLine method 
			BufferedReader dataIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			dataOut.writeUTF("deposita-" + valore);
			
			String result = dataIn.readLine();	// forza il proxy ad attendere una risposta dal server
												// nel caso di metodo che restituisce void
			
			s.close();

			// rispondo tramite JMS al client Python
			TextMessage message = qsession.createTextMessage(result + "-" + valore);
			QueueSender sender = qsession.createSender(qresponse);
			sender.send(message);
		
		// da aggiungere per gestire problemi Proxy su socket
		}catch (UnknownHostException u ){
			u.printStackTrace();
		}catch (IOException e ){
			e.printStackTrace();
		
		// da aggiungere per gestire problemi JMS
		} catch (JMSException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int preleva(){
		
		String x = null;
		
		try{
			
			Socket s = new Socket ( addr, port );

			DataOutputStream dataOut = new DataOutputStream ( s.getOutputStream() );
			
			// NOTE: A BufferedReader is used to receive data from a Python application, since it allows using the readLine method 
			BufferedReader dataIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			dataOut.writeUTF("preleva");
			x=new String(dataIn.readLine());
			
			s.close();
		
			// rispondo tramite JMS al client Python
			TextMessage message = qsession.createTextMessage("Prelevato-" + x);
			QueueSender sender = qsession.createSender(qresponse);
			sender.send(message);

		}catch (UnknownHostException u ){
			u.printStackTrace();
		}catch (IOException e ){
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Integer.valueOf(x);
	}
	

}
