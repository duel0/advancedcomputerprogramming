package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    public static void main(String[] args) {
        try{
            String s = "Buongiorno Server!";
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(s.getBytes(), s.getBytes().length, InetAddress.getLocalHost(), 8050);

            System.out.println("[CLIENT] Invio pacchetto UDP...");
            socket.send(packet);
            System.out.println("[CLIENT] Pacchetto inviato");

            byte[] data = new byte[65508];
            DatagramPacket response = new DatagramPacket(data,data.length);

            System.out.println("[CLIENT] Attendo risposta");
            socket.receive(response);
            System.out.println("[CLIENT] Risposta ricevuta");

            s = new String (response.getData(), 0, response.getLength());
            System.out.println("[CLIENT] Contenuto: "+s);
            socket.close();
        } catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
