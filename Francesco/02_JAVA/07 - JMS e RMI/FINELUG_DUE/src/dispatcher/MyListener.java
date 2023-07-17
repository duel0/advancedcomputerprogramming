package dispatcher;

import javax.jms.*;

public class MyListener implements MessageListener{

    public void onMessage(Message message) {
        MapMessage m = (MapMessage) message;
        try {
            System.out.println("[LISTENER] Ricevuto Messaggio, passo al thread!");
            String documento = m.getString("documento");
            String printer = m.getString("printer");
            DispatcherThread t = new DispatcherThread(documento,printer);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
