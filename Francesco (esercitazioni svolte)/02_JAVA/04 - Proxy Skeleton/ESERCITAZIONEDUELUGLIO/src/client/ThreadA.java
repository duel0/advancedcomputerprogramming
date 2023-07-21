package client;

import java.util.Random;

import service.IMagazzino;

public class ThreadA extends Thread{
    public ThreadA(){
        super();
    }

    public void run(){
        IMagazzino m = new MagazzinoProxy();
        for(int i=0; i<3; i++){
            int val = (int)(Math.random()*(4-2+1)) +2;
            try {
                Thread.sleep(1000*val);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Random r = new Random();
            int id =r.nextInt(100);
            if(i%2==0){
                
                m.deposita("laptop",id );
                System.out.println("[CLIENTA] Deposito laptop con id: "+id);
            } else {
                System.out.println("[CLIENTA] Deposito smartphone con id: "+id);
                m.deposita("smartphone", id);
            }
        }
    }
}
