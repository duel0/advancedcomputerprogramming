package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter{

    public PrinterImpl() throws RemoteException{
        super();
    }
    public synchronized void printDoc(String doc) throws RemoteException {
        System.out.println("[PRINTERIMPL] Stampo il documento: "+doc);
        try {
            Thread.sleep(5000);
            FileWriter fw = new FileWriter("docs.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(doc+"\n");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
