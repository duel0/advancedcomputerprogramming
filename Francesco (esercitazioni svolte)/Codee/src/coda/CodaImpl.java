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
        head=tail=elem=0;
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
    public void inserisci(int i) {
        data[tail%size]=i;
        tail++;
        elem++;
    }

    @Override
    public int preleva() {
        int val = data[head%size];
        head++;
        elem--;
        return val;
    }
    
}
