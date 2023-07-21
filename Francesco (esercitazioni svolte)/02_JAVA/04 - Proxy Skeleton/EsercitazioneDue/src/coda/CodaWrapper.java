package coda;

import service.ICoda;

public abstract class CodaWrapper implements ICoda{
    protected ICoda coda;

    public CodaWrapper(ICoda c){
        coda=c;
    }

    public boolean empty(){
        return coda.empty();
    }

    public boolean full(){
        return coda.full();
    }

    public int size(){
        return coda.size();
    }
}
