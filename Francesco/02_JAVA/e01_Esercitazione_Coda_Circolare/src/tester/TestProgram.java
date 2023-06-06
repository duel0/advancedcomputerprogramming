package tester;

import coda.*;
import codaimpl.*;

public class TestProgram{
    public static void main(String[] args){
        Coda coda = new CodaCircolare(5);
        Coda wrapper = new CodaWrapperSem(coda);

        int nthreads = 100;
        WorkerThread[] workers = new WorkerThread[nthreads];

        for(int i=0; i<nthreads; i++){
            if(i%2==0){
                workers[i] = new WorkerThread(wrapper,true);
            } else {
                workers[i] = new WorkerThread(wrapper,false);
            }

            workers[i].start();
        }

        for(int i=0; i<nthreads; i++){
            try{
                workers[i].join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}