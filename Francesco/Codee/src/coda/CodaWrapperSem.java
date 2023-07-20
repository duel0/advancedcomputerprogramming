package coda;

import java.util.concurrent.Semaphore;

import service.ICoda;

public class CodaWrapperSem extends CodaWrapper{

    private Semaphore spazioDisp;
    private Semaphore elemDisp;

    public CodaWrapperSem(ICoda c){
        super(c);
        spazioDisp = new Semaphore(c.size());
        elemDisp = new Semaphore(0);
    }
    @Override
    public void inserisci(int i) {
        try {
            spazioDisp.acquire();
            synchronized(coda){
                coda.inserisci(i);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            elemDisp.release();
        }
    }

    @Override
    public int preleva() {
        int val=0;
        try {
            elemDisp.acquire();
            synchronized(coda){
                val=coda.preleva();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            spazioDisp.release();
        }
        return val;
    }
    
}
