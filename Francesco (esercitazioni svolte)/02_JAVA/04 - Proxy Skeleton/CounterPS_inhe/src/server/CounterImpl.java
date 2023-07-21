package server;

public class CounterImpl extends CounterSkel{
    private int value;

    public CounterImpl(){
        value=0;
    }

    public synchronized void inc(){
        value++;
    }

    public synchronized void square(){
        value=value*value;
    }

    public synchronized void sum(int i){
        value=value+i;
    }

    public int get(){
        return value;
    }
}
