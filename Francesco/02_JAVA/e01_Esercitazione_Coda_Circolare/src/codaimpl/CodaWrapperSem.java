package codaimpl;

import java.util.concurrent.Semaphore;

import coda.*;

public class CodaWrapperSem extends CodaWrapper{
    private Semaphore postiDisp;
    private Semaphore elemDisp;

    public CodaWrapperSem(Coda c){
        super(c);
        postiDisp = new Semaphore(c.getSize());
        elemDisp = new Semaphore(0);
    }

    public void inserisci(int i){
        try{
            postiDisp.acquire();
            try{
                synchronized(c){
                    c.inserisci(i);
                }
            } finally{
                elemDisp.release();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int preleva(){
        int x=0;
        try{
            elemDisp.acquire();
            try{
                x=c.preleva();
            } finally{
                postiDisp.release();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return x;
    }
}
