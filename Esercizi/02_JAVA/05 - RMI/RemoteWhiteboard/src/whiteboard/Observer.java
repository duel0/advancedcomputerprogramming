package whiteboard;

import java.rmi.*;

/*
 * Interfaccia callback. Il client (main program in WhiteboardClient2.java)
 * crea un oggetto di tipo Observer che sara' invocabile 'remotamente'
 * dal lato server.
 */

public interface Observer extends Remote{

	/*
	 * callbackMethod
	 */
	
	public void observerNotify () throws RemoteException ;
	
}
