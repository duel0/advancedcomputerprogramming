package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter{
    
    private static final long serialVersionUID=10;

    protected PrinterImpl() throws RemoteException {
        
        //TODO Auto-generated constructor stub
    }

    public synchronized void printDoc(String doc) throws RemoteException {
        try {
            Thread.sleep(5000);
            System.out.println("Scrivo il documento: "+doc);
            FileWriter writer = new FileWriter("docs.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter w = new PrintWriter(bw);
            w.write(doc+"\n");
            w.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
