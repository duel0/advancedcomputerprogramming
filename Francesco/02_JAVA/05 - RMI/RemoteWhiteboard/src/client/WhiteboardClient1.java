package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.Shape;
import whiteboard.Whiteboard;

public class WhiteboardClient1 {
    public static void main(String[] args) {
        try {
            Registry rmireRegistry = LocateRegistry.getRegistry();
            Whiteboard board = (Whiteboard) rmireRegistry.lookup("whiteboard");
            Shape s;
            int x;

            for ( int i =0; i < 4; i++){
				x = (int)(1+Math.random()*10);
				
				if ( x <= 5)
					s=new Triangle();
				else
					s=new Square();
				
				System.out.println ("\nAdding the shape (" + x + ")" + s.toString() );
				
				//invocazione remota. L'argomento Shape e' un oggetto non-remoto 
				board.addShape(s);
				
				Thread.sleep(10000);
			}

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
