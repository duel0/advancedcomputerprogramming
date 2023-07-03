package sub;

import coda.ICoda;

public class TManager extends Thread{
    private ICoda coda;
    private String msg;

    public TManager(ICoda c, String msg){
        coda=c;
        this.msg=msg;
    }
    public void run(){
        coda.inserisci(msg);
        System.out.println("[TMANAGER] Ho inserito nella coda: "+msg);
    }
}
