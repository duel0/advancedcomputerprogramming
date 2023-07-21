package client;

import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try{
            DatagramSocket s = new DatagramSocket();
            String str = "Ciao cicciolo come stai?";
            DatagramPacket request = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getLocalHost(), 8500);
            s.send(request);
            byte[] data = new byte[65508];
            DatagramPacket response = new DatagramPacket(data, data.length);
            s.receive(response);
            String resppp = new String(response.getData(), 0, response.getLength());
            System.out.println("[CLIENT] Ricevo: "+resppp);
            s.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}