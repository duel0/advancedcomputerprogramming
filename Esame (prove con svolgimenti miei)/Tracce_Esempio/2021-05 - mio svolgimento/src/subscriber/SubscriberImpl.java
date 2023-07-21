package subscriber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import service.ISubscriber;

public class SubscriberImpl implements ISubscriber {

    private String file;

    public SubscriberImpl(String f){
        file=f;
    }
    @Override
    public void notifyAlert(int criticality) {
        System.out.println("[SUBSCRIBER] Ricevo alert con criticità: "+criticality);
        try {
            FileWriter fw =new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(String.valueOf(criticality)+"\n");
            pw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
