package prodcons;

public class Buffer {
    private long value;
    private boolean full;

    public Buffer(){
        this.full=false;
        this.value=0;
    }

    synchronized void produci(){
        System.out.println(Thread.currentThread().getName() + ": vorrei produrre");
        while(full){
            System.out.println(Thread.currentThread().getName() + ": attendo che si svuota");
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        value = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ": ho prodotto "+value);
        full=true;
        notifyAll();
    }

    synchronized void consuma(){
        System.out.println(Thread.currentThread().getName() + ": vorrei consumare");
        while(!full){
            System.out.println(Thread.currentThread().getName() + ": attendo che si riempia");
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + ": valore letto "+value);
        full=false;
        notifyAll();
    }
}