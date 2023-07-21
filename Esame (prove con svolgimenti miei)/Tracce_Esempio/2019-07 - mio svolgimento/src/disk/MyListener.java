package disk;

import javax.jms.*;

public class MyListener implements MessageListener{

    @Override
    public void onMessage(Message message) {
        MapMessage m = (MapMessage) message;
        System.out.println("[DISK-LISTENER] Ricevuto!");
        try {
            DiskThread t = new DiskThread(m);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
