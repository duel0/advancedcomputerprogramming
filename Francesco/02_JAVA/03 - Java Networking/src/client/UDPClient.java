package client;

import java.net.*;
import java.io.IOException;

public class UDPClient {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            String s = "Ciao Server!!!";
            DatagramPacket packet = new DatagramPacket(s.getBytes(),s.getBytes().length, InetAddress.getLocalHost(), 8050);
            System.out.println("[CLIENT] invio pacchetto...");
            socket.send(packet);

            byte[] data = new byte[65508];
            DatagramPacket response = new DatagramPacket(data, data.length);

            System.out.println("[CLIENT] attendo pacchetto");
            socket.receive(response);
            System.out.println("[CLIENT] ricevuto!");

            s = new String(response.getData(), 0, response.getLength());
            System.out.println("[CLIENT] Ricevuto: "+s);

            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
