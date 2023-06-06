package codaimpl;
import coda.*;

public class CodaCircolare implements Coda{

    private int data[];

    private int size;
    private int elem;

    private int head;
    private int tail;

    public CodaCircolare(int size){
        this.size=size;
        elem=0;
        head=0;
        tail=0;
        data = new int[size];
    }

    public boolean full(){
        if(elem==size){
            return true;
        }
        return false;
    }

    public boolean empty(){
        if(elem==0){
            return true;
        }
        return false;
    }

    public int getSize(){
        return size;
    }

    public void inserisci(int i){
        data[tail%size]=i;
        try{
            Thread.sleep(101+(int)(Math.random()*100));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        elem=elem+1;
        System.out.println ("inserito " + i + " (tot = " + elem + " )");
        tail=tail+1;
    }

    public int preleva(){
        int i = data[head%size];
        try{
            Thread.sleep(101+(int)(Math.random()*100));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        elem=elem-1;
        System.out.println ("				prelevato " + i + " (tot = " + elem + " )");
        head=head+1;
        return i;
    }

}
