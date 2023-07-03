package threadpipe;

import java.io.*;

public class WriterThread extends Thread {
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
                System.out.println("[Writer] stringa letta: '"+s+"'");
                dataOut.writeUTF(s);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}