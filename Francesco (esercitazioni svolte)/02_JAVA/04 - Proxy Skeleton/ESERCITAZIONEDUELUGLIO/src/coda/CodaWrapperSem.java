package coda;

import java.util.concurrent.Semaphore;

import service.ICoda;

public class CodaWrapperSem extends CodaWrapper{

    private Semaphore spazioDisp;
    private Semaphore elemDisp;
    public CodaWrapperSem(ICoda c){
        super(c);
        spazioDisp = new Semaphore(coda.size());
        elemDisp = new Semaphore(0);
    }
    @Override
    public void deposita(int id) {
        try {
            spazioDisp.acquire();
            try {
                synchronized(coda){
                    coda.deposita(id);
                }
            } finally {
                elemDisp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int preleva() {
        int val=0;
        try {
            elemDisp.acquire();
            try{
                synchronized(coda){
                    val = coda.preleva();
                }
            } finally {
                spazioDisp.release();
            }
        } catch (Exception e) {
        }
        return val;
    }
    
}