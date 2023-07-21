package server;

import service.ILogger;

public class WorkerThread extends Thread{
    private int dato;
    private ILogger logger;

    public WorkerThread(int d, ILogger l){
        dato=d;
        logger=l;
    }

    public void run(){
        System.out.println("[WORKER] Ricevo dato: "+dato);
        logger.registraDato(dato);
    }
}
