package coda;

public abstract class CodaWrapper implements Coda{
    protected Coda c;

    public CodaWrapper(Coda coda){
        this.c=coda;
    }

    public int getSize(){
        return c.getSize();
    }

    public boolean full(){
        return c.full();
    }

    public boolean empty(){
        return c.empty();
    }
}
