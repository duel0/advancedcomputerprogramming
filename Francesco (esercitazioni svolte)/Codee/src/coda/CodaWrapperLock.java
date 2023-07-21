package coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import service.ICoda;

public class CodaWrapperLock extends CodaWrapper {
    private Lock lock;
    private Condition cv_prod;
    private Condition cv_cons;
    public CodaWrapperLock(ICoda c){
        super(c);
        lock = new ReentrantLock();
        cv_prod = lock.newCondition();
        cv_cons = lock.newCondition();
    }
    @Override
    public void inserisci(int i) {
        lock.lock();
        try{
            while(coda.full()){
                try {
                    cv_prod.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            coda.inserisci(i);
            cv_cons.signal();
        } finally {
            lock.unlock();
        }
    }
    @Override
    public int preleva() {
        int val=0;
        lock.lock();
        try{
            while(coda.full()){
                try {
                    cv_cons.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            val = coda.preleva();
            cv_prod.signal();
        } finally {
            lock.unlock();
        }
        return val;
    }
}
