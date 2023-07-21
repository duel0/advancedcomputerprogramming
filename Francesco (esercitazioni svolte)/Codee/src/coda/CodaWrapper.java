package coda;

import service.ICoda;

public abstract class CodaWrapper implements ICoda{

    protected ICoda coda;

    public CodaWrapper(ICoda c){
        coda = c;
    }

    @Override
    public int size() {
        return coda.size();
    }

    @Override
    public boolean full() {
        return coda.full();
    }

    @Override
    public boolean empty() {
        return coda.empty();
    }
    
}
