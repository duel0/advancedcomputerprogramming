package sub;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import coda.ICoda;

public class SensorListener implements MessageListener{
    private ICoda coda;
    public SensorListener(ICoda c){
        coda=c;
    }
    public void onMessage(Message arg){
        TextMessage msg = (TextMessage) arg;
        System.out.println("[LISTENER] Ricevo messaggy");
        try{
            String mess = msg.getText();
            System.out.println("[LISTENER] Istruzione letta: "+mess);
            TManager manager = new TManager(coda, mess);
            manager.start();
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
