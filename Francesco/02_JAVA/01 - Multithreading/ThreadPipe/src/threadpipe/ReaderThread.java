package threadpipe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ReaderThread extends Thread {
    private DataInputStream dataIn;
    public ReaderThread(PipedOutputStream pipeOut){
        try{
            dataIn = new DataInputStream(new PipedInputStream(pipeOut));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        String s;
        while (true){
            try{
                System.out.println("[READER] Waiting...");
                s=dataIn.readUTF();
                System.out.println("[READER] Read: "+s);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
