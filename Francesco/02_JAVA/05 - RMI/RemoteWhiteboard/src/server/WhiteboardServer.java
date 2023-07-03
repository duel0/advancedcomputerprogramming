package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.*;

public class WhiteboardServer {
    public static void main(String[] args) {
       try{ 
            Whiteboard w = new WhiteboardImpl();
            Registry rmiregistry = LocateRegistry.getRegistry();
            rmiregistry.rebind("whiteboard", w);
            System.out.println("Effettuato Rebind!");

       } catch (Exception e){
            e.printStackTrace();
       }

    }
}
