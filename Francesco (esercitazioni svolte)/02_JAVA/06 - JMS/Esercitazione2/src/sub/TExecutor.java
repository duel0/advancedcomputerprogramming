package sub;

//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import coda.ICoda;

public class TExecutor extends Thread{
    private ICoda coda;
    private File myFile;

    public TExecutor(ICoda c){
        coda=c;
        try{
            myFile = new File("CmdLog.txt");
            if(myFile.createNewFile()){
                System.out.println("File Creato!");
            } else {
                System.out.println("Ottenuto riferimento al file!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }   
        
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(10000);
                while(!coda.empty()){
                    String x = coda.preleva();
                    System.out.println("[TEXECUTOR] Prelevo: "+x);
                    try {
                        FileWriter writer=new FileWriter(myFile);
                        writer.append("\n"+x);
                        writer.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
