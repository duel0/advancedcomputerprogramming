package whiteboard;

import java.io.*;

/*
 * extends Serializable per consentire il trasferimento
 * di oggetti non-remoti Shape come argomenti di una invocazione
 * RMI
 */

/*
	se proviamo a non estendere Serializable avremmo un'eccezione sulle 
	operazioni di marshalling
	
*/

public interface Shape extends Serializable { //FA RIFERIMENTO AL TRASFERIMENTO DI OGGETTI NON REMOTI!!!

	public void draw ();

}
