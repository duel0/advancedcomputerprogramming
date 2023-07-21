package client;

import service.IMagazzino;

public class ClientThread extends Thread{

    private IMagazzino magazzino;
    private String op;

    public ClientThread(IMagazzino m, String op){
        magazzino=m;
        this.op=op;
    }

    public void run(){
        int articolo=0;
        for(int i=0; i<3; i++){
            try {
                Thread.sleep(1000*(((int)Math.random()*3)+2));
                articolo = (int)(Math.random()*101)+1;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            String art="";
            if(articolo%2==0){
                art="laptop";
            } else {
                art="smartphone";
            }

            if(op.equals("deposita")){
                int x = (int)(Math.random()*101)+1;
                System.out.println("[CLIENT] Deposito "+art+" con id "+x);
                magazzino.deposita(art, x);
            } else if (op.equals("preleva")){
                System.out.println("[CLIENT] Vedo di prelevare "+art);
                int x = magazzino.preleva(art);
                System.out.println("[CLIENT] Prelevo "+art+" con id "+x);

            }
        }
    }
    
}
