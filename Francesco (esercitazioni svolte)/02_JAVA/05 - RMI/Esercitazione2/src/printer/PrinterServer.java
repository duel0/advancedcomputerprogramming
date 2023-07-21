package printer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;
import service.IPrinter;

public class PrinterServer {
    public static void main(String[] args) {
        try {
            if(args.length!=1) return;
            IPrinter p = new PrinterImpl();
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher stub = (IDispatcher) rmiRegistry.lookup("dispatcher");
            System.out.println("Aggiungo la printer in ascolto su "+args[0]);
            stub.addPrinter(Integer.parseInt(args[0]));
            PrinterSkeleton ps = new PrinterSkeleton(p,Integer.parseInt(args[0]));
            ps.runSkeleton();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
