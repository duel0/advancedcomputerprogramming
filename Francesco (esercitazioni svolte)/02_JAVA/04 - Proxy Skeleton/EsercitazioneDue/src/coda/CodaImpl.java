package coda;

import service.ICoda;

public class CodaImpl implements ICoda{

    int data[];
    int head;
    int tail;
    int size;
    int elem;

    public CodaImpl(int dim){
        size=dim;
        data = new int[size];
        head=tail=elem=0;
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
    public int size() {
        return size;
    }

    @Override
    public void inserisci(int i) {
        data[tail%size]=i;
        tail=tail+1;
        elem=elem+1;
    }

    @Override
    public int preleva() {
        int x=0;
        x=data[head%size];
        head=head+1;
        elem=elem-1;
        return x;
    }
    
}
