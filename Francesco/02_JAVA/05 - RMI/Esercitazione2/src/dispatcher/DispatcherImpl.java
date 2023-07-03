package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import service.IDispatcher;
import service.IPrinter;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher{

    private Vector<IPrinter> printers;

    public DispatcherImpl() throws RemoteException{
        super();
        printers = new Vector<IPrinter>();
    }

    @Override
    public void addPrinter(int i) throws RemoteException {
        System.out.println("[DISPATCHER] Aggiungo printer sul porto "+i);
        IPrinter p = new PrinterProxy("127.0.0.1", i);
        printers.add(p);
    }

    @Override
    public boolean printRequest(String docName) throws RemoteException {
        for(int i=0; i<printers.size(); i++){
            if(printers.get(i).print(docName)==true){
                return true;
            }
        }
        return false;
    }
    
}
