package disk;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import service.ILogger;

public class LoggerProxy implements ILogger{
    private int port;

    public LoggerProxy(int port){
        this.port=port;
    }

    @Override
    public void registraDato(int dato) {
        try {
            DatagramSocket sock = new DatagramSocket();
            String s = String.valueOf(dato);
            DatagramPacket p = new DatagramPacket(s.getBytes(), s.getBytes().length, InetAddress.getLocalHost(), port);
            sock.send(p);
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
