package coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper{
    
    private Lock lock;
    private Condition spazioDisp;
    private Condition comandiDisp;

    public CodaWrapperLock(ICoda coda){
        super(coda);
        lock = new ReentrantLock();
        spazioDisp = lock.newCondition();
        comandiDisp = lock.newCondition();
    }

    public void inserisci(String i){
        try{
            lock.lock();
            while(c.full()){
                try{
                    spazioDisp.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            c.inserisci(i);
            comandiDisp.signal();
        } finally {
            lock.unlock();
        }
    }

    public String preleva(){
        String x = "";
        try{
            lock.lock();
            while(c.empty()){
                try {
                    comandiDisp.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            x = c.preleva();
            spazioDisp.signal();
        } finally{
            lock.unlock();
        }
        return x;
    }
}
