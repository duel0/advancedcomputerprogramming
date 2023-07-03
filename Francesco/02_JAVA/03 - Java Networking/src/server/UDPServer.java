package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(8050);
            byte[] data = new byte[65508];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            System.out.println("[SERVER] Attendo pacchetto...");
            socket.receive(packet);
            System.out.println("[SERVER] Pacchetto ricevuto!");

            byte[] receivedData = packet.getData();
            String s = new String(receivedData, 0, packet.getLength());
            System.out.println("[SERVER] Contenuto: "+s);

            s = "Ciao ciccio ti ho ricevuto!";

            DatagramPacket response = new DatagramPacket(s.getBytes(), s.getBytes().length, packet.getAddress(), packet.getPort());
            Thread.sleep(5000);
            System.out.println("[SERVER] Invio risposta!");
            socket.send(response);
            System.out.println("[SERVER] Risposta inviata!");
            socket.close();

        } catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
