package tester;
import coda.*;

public class WorkerThread extends Thread{
    private Coda wrapper;
    private boolean flag;

    public WorkerThread(Coda w, boolean f){
        wrapper=w;
        flag=f;
    }

    public void run(){
        if(flag){
            wrapper.inserisci(1+(int)(Math.random()* 100));
        } else {
            wrapper.preleva();
        }
    }
}
