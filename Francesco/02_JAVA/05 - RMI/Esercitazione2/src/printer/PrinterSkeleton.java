package printer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IPrinter;

public class PrinterSkeleton implements IPrinter{
    private IPrinter printer;
    private int port;

    public PrinterSkeleton(IPrinter p, int port){
        printer=p;
        this.port=port;
    }

    public void runSkeleton(){
        try {
            ServerSocket s = new ServerSocket(port);
            Socket socket = null;
            System.out.println("Inizializzo server...");
            while(true){
                socket = s.accept();
                PrinterThread t = new PrinterThread(socket,printer);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean print(String docName) {
        return printer.print(docName);
    }

    
}
