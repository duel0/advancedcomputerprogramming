package coda;

public class CodaImpl implements ICoda{
    private String[] data;
    private int elem;
    private int head;
    private int tail;
    private int size;

    public CodaImpl(int dim){
        size=dim;
        data = new String[size];
        elem=head=tail=0;
    }

    public int size(){
        return size;
    }

    public boolean full(){
        return elem==size;
    }

    public boolean empty(){
        return elem==0;
    }

    public void inserisci(String i){
        data[tail%size]=i;
        tail=tail+1;
        elem=elem+1;
    }

    public String preleva(){
        String x=data[head%size];
        head=head+1;
        elem=elem-1;
        return x;
    }
}
