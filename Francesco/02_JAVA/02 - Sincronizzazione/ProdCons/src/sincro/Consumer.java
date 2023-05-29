package sincro;
public class Consumer extends Thread{
    private Buffer buffer;
    
    public Consumer(Buffer b, String name){
        super(name);
        buffer=b;
    }

    public void run(){
        buffer.consuma();
    }
}
