package codaimpl;

import coda.*;

public class CodaWrapperSynch extends CodaWrapper{

    public CodaWrapperSynch(Coda c){
        super(c);
    }

    public int preleva(){
        int x = 0;
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
}
