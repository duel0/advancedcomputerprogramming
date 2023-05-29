package sincro;
public class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer b, String name){
        super(name);
        this.buffer=b;
    }

    public void run(){
        buffer.produci();
    }

}
