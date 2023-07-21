package dispatcher;

import javax.jms.*;

public class DispatcherMsgListener implements MessageListener{

    private int port;
    private QueueSession qs;
    private Queue r;

    public DispatcherMsgListener(int p, QueueSession s, Queue q){
        port=p;
        qs=s;
        r=q;
    }
    @Override
    public void onMessage(Message arg) {
        TextMessage msg = (TextMessage) arg;
        try {
            String message = msg.getText();
            System.out.println("[LISTENER] Ricevuto: "+message);
            IDispatcher d = new DispatcherProxy("127.0.0.1", port, qs, r);
            if(message.equalsIgnoreCase("preleva")){
                int x = d.preleva();
                System.out.println("[LISTENER] Prelevo: "+x);
            } else {
                String value = message.split("-")[1];
                d.deposita(Integer.valueOf(value));
                System.out.println("[LISTENER] Deposito "+value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}