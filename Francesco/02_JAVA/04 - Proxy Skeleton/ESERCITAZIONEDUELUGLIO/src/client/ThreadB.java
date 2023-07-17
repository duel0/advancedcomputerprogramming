package client;

import service.IMagazzino;

public class ThreadB extends Thread{
    public ThreadB(){
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
            int id=0;
            if(i%2==0){
                id= m.preleva("laptop");
                System.out.println("[CLIENTB] Prelevo laptop con id: "+id);
            } else {
                id= m.preleva("smartphone");
                System.out.println("[CLIENTA] Prelevo smartphone con id: "+id);
            }
        }
    }
}
