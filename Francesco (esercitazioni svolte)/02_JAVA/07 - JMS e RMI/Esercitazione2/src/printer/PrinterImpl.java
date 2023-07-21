package printer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import service.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter{
    private Lock lock;

    public PrinterImpl() throws RemoteException{
        lock = new ReentrantLock();
    }

    @Override
    public void printDoc(String s) throws RemoteException {
        lock.lock();
        try{
            System.out.println("Leggo: "+s);
        } finally{
            lock.unlock();
        }
    }
    
}
