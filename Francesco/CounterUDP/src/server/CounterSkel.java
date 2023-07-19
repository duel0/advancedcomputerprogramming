package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import service.ICounter;

public abstract class CounterSkel implements ICounter{

    public void runSkeleton(){
        try {
            DatagramSocket sock = new DatagramSocket(8500);
            while(true){
                byte[] data = new byte[65508];
                DatagramPacket p = new DatagramPacket(data, data.length);
                sock.receive(p);
                WorkerThread t = new WorkerThread(p, this);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
