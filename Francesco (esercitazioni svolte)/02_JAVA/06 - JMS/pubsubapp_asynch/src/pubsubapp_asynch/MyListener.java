package pubsubapp_asynch;

import javax.jms.*;

public class MyListener implements MessageListener{
    public void onMessage(Message arg0){
        TextMessage mymsg = (TextMessage) arg0;
        System.out.println("Receiving messages...");
        try{
            String msg = mymsg.getText();
            System.out.println("Subscriber: leggo il messaggio -> "+msg);
            System.out.println("La int property è "+mymsg.getIntProperty("propInt"));
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
