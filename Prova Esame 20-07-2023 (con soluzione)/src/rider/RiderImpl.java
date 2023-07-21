package rider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import service.IRider;

public class RiderImpl implements IRider{

    private int location;
    private String filename;

    public RiderImpl(int loc, String filename){
        location=loc;
        this.filename=filename;
    }

    @Override
    public int getLocation() {
        return location;
    }

    @Override
    public void notifyOrder(int o, String add) {
        System.out.println("[RIDER-"+location+"] Ricevo ordine: "+o+" all'indirizzo: "+add);
        FileWriter fw;
        try {
            // Scrittura su file con append=true
            fw = new FileWriter(filename, true);
        
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.write("Ordine: "+o+"; Location: "+location+"; Via: "+add+"\n");
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
