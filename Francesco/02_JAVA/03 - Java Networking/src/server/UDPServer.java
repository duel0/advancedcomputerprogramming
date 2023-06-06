package server;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(8050);
            byte[] data = new byte[65508];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            System.out.println("[SERVER] attendo pacchetto...");
            socket.receive(packet);
            System.out.println("[SERVER] pacchetto ricevuto");

            byte[] receivedData = packet.getData();

            String s = new String(receivedData,0,packet.getLength());
            System.out.println("[Server]: contenuto pacchetto: "+s);

            s = "Ok client, pacchetto ricevuto";

            DatagramPacket response = new DatagramPacket(s.getBytes(), s.getBytes().length, packet.getAddress(), packet.getPort());
            Thread.sleep(5000);
            System.out.println("[Server]: rispondo...");
            socket.send(response);
            System.out.println("[Server]: pacchetto inviato.");
            socket.close();
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
