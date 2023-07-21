package coda;

import service.ICoda;

public class CodaWrapperSync extends CodaWrapper{


    public CodaWrapperSync(ICoda c){
        super(c);
    }

    @Override
    public void inserisci(int i) {
        synchronized(coda){
            while(coda.full()){
                try{
                    coda.wait();
                    System.out.println("Coda Piena!");
                }
               catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            coda.inserisci(i);
            coda.notifyAll();
        }
    }

    @Override
    public int preleva() {
        int x=0;
        synchronized(coda){
            while(coda.empty()){
                try{
                    coda.wait();
                    System.out.println("Coda vuota!");
                }
               catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            x = coda.preleva();
            coda.notifyAll();
        }
        return x;
    }
    
}
