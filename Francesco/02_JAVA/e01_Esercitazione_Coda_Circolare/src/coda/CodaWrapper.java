package coda;

public abstract class CodaWrapper implements Coda{
    
    protected Coda c;

    public CodaWrapper(Coda c){
        this.c=c;
    }

    public int getSize(){
        return c.getSize();
    }

    public boolean empty(){
        return c.empty();
    }

    public boolean full(){
        return c.full();
    }

}
