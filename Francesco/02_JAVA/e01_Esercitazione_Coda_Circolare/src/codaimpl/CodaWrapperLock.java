package codaimpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        } finally {
            lock.unlock();
        }
    }

    public int preleva(){
        lock.lock();
        int i;
        try{
            while(c.empty()){
                try{
                    full.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            i = c.preleva();
            empty.signal();
        } finally{
            lock.unlock();
        }
        return i;
    }
}
