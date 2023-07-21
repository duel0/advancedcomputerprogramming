package node;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class Node {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            IDispatcher stub = (IDispatcher) registry.lookup("dispatcher");
            NodeThread[] threads = new NodeThread[5];
            for(int i=0; i<5; i++){
                NodeThread thread = new NodeThread(stub);
                thread.start();
                threads[i]=thread;
            }
            for(int i=0; i<5; i++){
                threads[i].join();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
