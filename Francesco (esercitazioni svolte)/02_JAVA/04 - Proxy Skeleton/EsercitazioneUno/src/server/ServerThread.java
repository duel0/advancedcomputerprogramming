package server;

import dispatcher.*;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {

	private Socket s; 
	private IDispatcher dispatcher; 
	
	public ServerThread ( Socket sk, IDispatcher d ){
		s=sk;
		dispatcher = d; 
	}
	
	
	public void run ( ){
		
		System.out.println ("	[SrvThread] run thread! " );
		
		try{
			
			
			DataInputStream dataIn = new DataInputStream ( s.getInputStream() );
			DataOutputStream dataOut = new DataOutputStream ( s.getOutputStream() );
			
			String method = dataIn.readUTF();
			int x;
			
			if ( method.compareTo("sendCmd") == 0 ){
				
				x=dataIn.readInt();
				System.out.println ("	[SrvThread] method: " + method + ", " + x);
				
				dispatcher.sendCmd (  x);
				
				dataOut.writeUTF("ack");
				
			}
			else if ( method.compareTo("getCmd") == 0 ){
				
			
				System.out.println ("	[SrvThread] method: " + method + ", -");
				x= dispatcher.getCmd(); 
				
				dataOut.writeInt ( x);
				
			}else
				System.out.println ("Error: unknown method!");
			
			
			System.out.println ();
			s.close();
			
			
		}catch ( IOException e ){
			e.printStackTrace();
		}
		
	}
	
	
}
