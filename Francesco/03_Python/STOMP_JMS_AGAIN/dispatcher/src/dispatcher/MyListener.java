package dispatcher;

import javax.jms.*;

public class MyListener implements MessageListener{

    private QueueSession qsession;
    private Queue qresponse;

    public MyListener(QueueSession qs, Queue r){
        qsession=qs;
        qresponse=r;
    }
    @Override
    public void onMessage(Message message) {
        TextMessage m = (TextMessage) message;
        try {
            String msg = m.getText();
            System.out.println("[LISTENER] Ricevo: "+msg);
            DispatcherProxy dispatcher = new DispatcherProxy(qsession, qresponse);
            if(msg.equalsIgnoreCase("preleva")){
                dispatcher.preleva();
            } else {
                int val = Integer.parseInt(msg.split("-")[1]);
                dispatcher.deposita(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

} 