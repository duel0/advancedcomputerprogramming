/**
 * 
 */
/**
 * @author Francesco
 *
 */
module HelloWorldRMI {
	requires java.rmi;
	exports server to java.rmi;
	exports service to java.rmi;
}