package sincro;
public class Buffer {
    private long content;
    private boolean full;

    public Buffer(){
        this.content=0;
        this.full=false;
    }

    public synchronized void produci(){
        System.out.println(Thread.currentThread().getName()+" invocazione produci");
        while(full){
            System.out.println(Thread.currentThread().getName()+" buffer pieno, attendo...");
            try{
                wait();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        content=System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+" prodotto: "+content);
        full=true;
        notifyAll();
    }

    public synchronized void consuma(){
        System.out.println(Thread.currentThread().getName()+" invocazione consuma");
        while(!full){
            System.out.println(Thread.currentThread().getName()+" buffer vuoto, attendo...");
            try{
                wait();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" consumato: "+content);
        full=false;
        notifyAll();
    }
}
