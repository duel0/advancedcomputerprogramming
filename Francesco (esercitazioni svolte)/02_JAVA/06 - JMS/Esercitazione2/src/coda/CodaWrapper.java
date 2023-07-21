package coda;

public abstract class CodaWrapper implements ICoda{
    protected ICoda c;

    public CodaWrapper(ICoda coda){
        this.c=coda;
    }

    public int size(){
        return c.size();
    }

    public boolean full(){
        return c.full();
    }

    public boolean empty(){
        return c.empty();
    }
}
