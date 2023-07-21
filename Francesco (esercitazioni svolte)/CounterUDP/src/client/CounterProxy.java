package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import service.ICounter;

public class CounterProxy implements ICounter{

    private DatagramSocket s;

    public CounterProxy(){
        try {
            s = new DatagramSocket();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void inc() {
        String toSend = "inc#";
        try {
            DatagramPacket p = new DatagramPacket(toSend.getBytes(), toSend.getBytes().length, InetAddress.getLocalHost(), 8500);
            s.send(p);
            s.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void square() {
        String toSend = "square#";
        try {
            DatagramPacket p = new DatagramPacket(toSend.getBytes(), toSend.getBytes().length, InetAddress.getLocalHost(), 8500);
            s.send(p);
            s.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int get() {
        int val=0;
        String toSend = "get#";
        try {
            DatagramPacket p = new DatagramPacket(toSend.getBytes(), toSend.getBytes().length, InetAddress.getLocalHost(), 8500);
            s.send(p);
            byte[] data = new byte[65508];
            DatagramPacket resp = new DatagramPacket(data, data.length);
            s.receive(resp);
            String result = new String(resp.getData(),0,resp.getLength());
            val=Integer.parseInt(result);
            s.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return val;
    }

    @Override
    public void sum(int val) {
        String toSend = "sum#"+val+"#";
        try {
            DatagramPacket p = new DatagramPacket(toSend.getBytes(), toSend.getBytes().length, InetAddress.getLocalHost(), 8500);
            s.send(p);
            s.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
