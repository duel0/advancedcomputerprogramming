package codaimpl;

import java.util.concurrent.locks.*;
import coda.*;

public class CodaWrapperLock extends CodaWrapper{
    private Lock lock;
    private Condition empty;
    private Condition full;

    public CodaWrapperLock(Coda c){
        super(c);
        lock = new ReentrantLock();
        empty = lock.newCondition();
        full = lock.newCondition();
    }

    public void inserisci(int i){
        lock.lock();
        try{
            while(c.full()){
                try{
                    empty.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            c.inserisci(i);
            full.signal();
        } finally{
            lock.unlock();
        }
    }

    public int preleva(){
        int x=0;
        lock.lock();
        try{
            while(c.empty()){
                try{
                    full.await();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            x=c.preleva();
            empty.signal();
        } finally {
            lock.unlock();
        }
        return x;
    }
}
