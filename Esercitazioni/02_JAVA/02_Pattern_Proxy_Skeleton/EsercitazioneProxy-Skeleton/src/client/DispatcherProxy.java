package client;

import java.net.*;
import java.io.*;

import dispatcher.*;

public class DispatcherProxy implements IDispatcher {
	
	private String addr;
	private int port;
	
	
	public DispatcherProxy ( String a, int p ){
		addr = new String ( a);
		port = p;	
	}
	
	
	public void sendCmd ( int cmd ){
		
		try{
			Socket s = new Socket ( addr, port );
			
			DataOutputStream dataOut = new DataOutputStream ( s.getOutputStream() );
			DataInputStream dataIn = new DataInputStream ( s.getInputStream() );
			
			dataOut.writeUTF("sendCmd");
			dataOut.writeInt ( cmd );
			
			dataIn.readUTF();	// forza il proxy ad attendere una risposta dal server
								// nel caso di metodo che restituisce void
			
			s.close();
			
		}catch (UnknownHostException u ){
			u.printStackTrace();
		}catch (IOException e ){
			e.printStackTrace();
		}
		
	}
	
	
	public int getCmd(){
		
		int x=0;
		
		try{
			
			Socket s = new Socket ( addr, port );
			DataOutputStream dataOut = new DataOutputStream ( s.getOutputStream() );
			DataInputStream dataIn = new DataInputStream ( s.getInputStream() );
			
			dataOut.writeUTF("getCmd");
			x=dataIn.readInt ();
			
			s.close();
			
		}catch (UnknownHostException u ){
			u.printStackTrace();
		}catch (IOException e ){
			e.printStackTrace();
		}
		
		return x;
	}
	

}
