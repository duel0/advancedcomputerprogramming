package coda;

import service.ICoda;

public class CodaImpl implements ICoda{

    private int[] data;
    private int size;
    private int elem;
    private int head;
    private int tail;

    public CodaImpl(int size){
        this.size=size;
        data = new int[size];
        elem=head=tail=0;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean full() {
        return elem==size;
    }

    @Override
    public boolean empty() {
        return elem==0;
    }

    @Override
    public void deposita(int id) {
        data[tail%size]=id;
        tail=tail+1;
        elem=elem+1;
    }

    @Override
    public int preleva() {
        int val = data[head%size];
        head=head+1;
        elem=elem-1;
        return val;
    }
    
}
