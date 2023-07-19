package server;

import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(8500);

            byte[] data = new byte[65508];
            DatagramPacket request = new DatagramPacket(data, data.length);
            s.receive(request);
            String str = new String(request.getData(),0,request.getLength());
            System.out.println("Ricevuto: "+str);
            String resp = "RICCHIOOOO";
            DatagramPacket response = new DatagramPacket(resp.getBytes(), resp.getBytes().length, request.getAddress(), request.getPort());
            s.send(response);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}