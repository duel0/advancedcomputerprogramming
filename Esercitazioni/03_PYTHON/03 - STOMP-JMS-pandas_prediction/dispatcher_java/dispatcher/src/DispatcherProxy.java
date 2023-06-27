

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
	
	
	public void forecast(int year){
		
		try{
			Socket s = new Socket ( addr, port );
			
			DataOutputStream dataOut = new DataOutputStream ( s.getOutputStream() );
			
			// NOTE: A BufferedReader is used to receive data from a Python application, since it allows using the readLine method 
			BufferedReader dataIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			dataOut.writeUTF("forecast-" + year);
			
			String result = dataIn.readLine();	// forza il proxy ad attendere una risposta dal server
												// nel caso di metodo che restituisce void
			
			s.close();

			// rispondo tramite JMS al client Python
			TextMessage message = qsession.createTextMessage("forecast-" + result);
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
	
	
	public float get_mean(){
		
		String result = null;
		
		try{
			
			Socket s = new Socket ( addr, port );

			DataOutputStream dataOut = new DataOutputStream ( s.getOutputStream() );
			
			// NOTE: A BufferedReader is used to receive data from a Python application, since it allows using the readLine method 
			BufferedReader dataIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			dataOut.writeUTF("get_mean");
			//x=new String(dataIn.readLine());
			result = new String(dataIn.readLine());
			
			s.close();
		
			// rispondo tramite JMS al client Python
			TextMessage message = qsession.createTextMessage("get_mean-" + result);
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
		
		return Float.valueOf(result);
	}
	

}
