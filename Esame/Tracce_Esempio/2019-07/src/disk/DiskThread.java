package disk;

import javax.jms.*;

public class DiskThread extends Thread{
    private MapMessage msg;
    public DiskThread(MapMessage m){
        msg=m;
    }

    public void run(){
        try {
            int dato = msg.getInt("dato");
            int porta = msg.getInt("porta");
            System.out.println("[DISK] Ricevo "+dato+" da inviare alla porta "+porta);
            LoggerProxy proxy = new LoggerProxy(porta);
            proxy.registraDato(dato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
