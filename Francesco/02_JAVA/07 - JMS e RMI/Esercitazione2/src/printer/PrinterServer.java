package printer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IPrinter;

public class PrinterServer {
    public static void main(String[] args) {
        if(args.length!=1) return;
        try {
            Registry rmRegistry = LocateRegistry.getRegistry();
            IPrinter printer = new PrinterImpl();
            rmRegistry.rebind(args[0], printer);
            System.out.println("[PRINTERSERVER] OK!");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
