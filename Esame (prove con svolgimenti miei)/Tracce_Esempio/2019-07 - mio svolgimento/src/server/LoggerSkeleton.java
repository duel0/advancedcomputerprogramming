package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import service.ILogger;

public abstract class LoggerSkeleton implements ILogger{

    private int port;

    public LoggerSkeleton(int p){
        port=p;
    }

    public void runSkeleton(){
        try {
            DatagramSocket s = new DatagramSocket(port);
            while(true){
                byte[] data = new byte[65508];
                DatagramPacket p = new DatagramPacket(data, data.length);
                s.receive(p);
                String str = new String(p.getData(),0,p.getLength());
                int dato = Integer.parseInt(str);
                WorkerThread t = new WorkerThread(dato,this);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
