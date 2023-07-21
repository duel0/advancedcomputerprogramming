package dispatcher;

import javax.jms.*;

public class DispatcherListener implements MessageListener{

    @Override
    public void onMessage(Message message) {
        MapMessage mess = (MapMessage) message;
        try {
            DispatcherThread t = new DispatcherThread(mess);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
