package codaimpl;

import coda.*;

public class CodaWrapperSynchr extends CodaWrapper{
    public CodaWrapperSynchr(Coda c){
        super(c);
    }

    public void inserisci(int i){
        synchronized(c){
            while(c.full()){
                try{
                    c.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            c.inserisci(i);
            c.notifyAll();
        }
    }

    public int preleva(){
        int x=0;
        synchronized(c){
            while(c.empty()){
                try{
                    c.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            x = c.preleva();
            c.notifyAll();
        }
        return x;
    }
}
