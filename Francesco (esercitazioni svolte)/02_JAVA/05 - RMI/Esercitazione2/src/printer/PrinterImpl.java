package printer;

import java.util.concurrent.Semaphore;

import service.IPrinter;

public class PrinterImpl implements IPrinter{

    private Semaphore mutex;

    public PrinterImpl(){
        mutex = new Semaphore(1);
    }

    public boolean print(String docName) {
        if(mutex.tryAcquire()){
            System.out.println("Printer Libera: stampo "+docName);
            mutex.release();
            return true;
        } else return false;
    }
}
