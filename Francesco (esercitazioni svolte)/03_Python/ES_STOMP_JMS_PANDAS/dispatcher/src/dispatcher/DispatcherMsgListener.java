package dispatcher;

import javax.jms.*;

public class DispatcherMsgListener implements MessageListener{
    private int port;
    private QueueSession qsession;
    private Queue qresponse;

    public DispatcherMsgListener(QueueSession s, Queue r, int p){
        port=p;
        qsession=s;
        qresponse=r;
    }

    @Override
    public void onMessage(Message message) {
        TextMessage mess = (TextMessage) message;
        DispatcherProxy dispatcher = new DispatcherProxy("127.0.0.1",port, qsession, qresponse);
        try {
            String messaggio = mess.getText();
            if(messaggio.contains("forecast")){
                String val = messaggio.split("-")[1];
                System.out.println("[DispatcherMsgListener] Ricevuta richiesta di forecast per l'anno " + val );
                dispatcher.forecast(Integer.valueOf(val));
            } else {
                System.out.println("[DispatcherMsgListener] Ricevuta richiesta di get_mean");
                dispatcher.get_mean();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
