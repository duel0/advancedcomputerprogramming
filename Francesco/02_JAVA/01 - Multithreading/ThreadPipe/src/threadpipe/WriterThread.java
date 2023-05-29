package threadpipe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedOutputStream;

public class WriterThread extends Thread{
    private DataOutputStream dataOut;

    public WriterThread (PipedOutputStream pipeOut){
        dataOut = new DataOutputStream(pipeOut);
    }

    public void run(){
        BufferedReader keyboardBuf = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(true){
            try{
                System.out.println("[Writer] enter string: ");
                s = keyboardBuf.readLine();
                System.out.println("[Writer] string: <"+s+"> output to pipe");
                dataOut.writeUTF(s);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}