package dispatcher;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MyListener implements MessageListener{

    @Override
    public void onMessage(Message message) {
        
        MapMessage m = (MapMessage) message;
        System.out.println("[DISPATCHER-LISTENER] Attendo messaggi...");
        try{
            DispatcherThread dt = new DispatcherThread(m);
            dt.start();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
}
