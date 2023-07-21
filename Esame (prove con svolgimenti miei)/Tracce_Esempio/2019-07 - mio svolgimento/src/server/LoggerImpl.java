package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LoggerImpl extends LoggerSkeleton{


    public LoggerImpl(int port){
        super(port);
    }

    @Override
    public synchronized void registraDato(int dato) {
        try {
            FileWriter fw = new FileWriter("logs.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write("Saved: "+String.valueOf(dato)+"\n");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
