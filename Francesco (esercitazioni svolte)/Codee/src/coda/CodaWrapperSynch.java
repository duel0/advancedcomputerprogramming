package coda;

import service.ICoda;

public class CodaWrapperSynch extends CodaWrapper{

    public CodaWrapperSynch(ICoda c){
        super(c);
    }
    @Override
    public void inserisci(int i) {
        synchronized(coda){
            while(coda.full()){
                try {
                    coda.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            coda.inserisci(i);
            coda.notifyAll();
        }
    }

    @Override
    public int preleva() {
        int val=0;
        synchronized(coda){
            while(coda.full()){
                try {
                    coda.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            val=coda.preleva();
            coda.notifyAll();
        }
        return val;
    }
    
}
