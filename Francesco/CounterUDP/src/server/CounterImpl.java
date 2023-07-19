package server;

public class CounterImpl extends CounterSkel{

    private int count;

    public CounterImpl(){
        count=0;
    }
    @Override
    public void inc() {
        count=count+1;
    }

    @Override
    public void square() {
        count=count*count;
    }

    @Override
    public int get() {
        return count;
    }

    @Override
    public void sum(int val) {
        count=count+val;
    }
    
}
