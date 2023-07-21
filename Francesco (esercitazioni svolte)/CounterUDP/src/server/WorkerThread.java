package server;

import java.net.*;
import java.util.StringTokenizer;

import service.ICounter;

public class WorkerThread extends Thread{
    private DatagramPacket p;
    private ICounter c;

    public WorkerThread(DatagramPacket p, ICounter c){
        this.p=p;
        this.c=c;
    }

    public void run(){
        String result = new String(p.getData(),0,p.getLength());
        StringTokenizer tokenizer = new StringTokenizer(result, "#");
        String command = tokenizer.nextToken();
        if(command.equals("get")){
            try {
                DatagramSocket s = new DatagramSocket();
                String value = String.valueOf(c.get());
                DatagramPacket pack = new DatagramPacket(value.getBytes(), value.getBytes().length, p.getAddress(), p.getPort());
                s.send(pack);
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("inc")){
            c.inc();
        } else if (command.equals("square")){
            c.square();
        } else if (command.equals("sum")){
            int val = Integer.valueOf(tokenizer.nextToken());
            c.sum(val);
        }
    }
}
