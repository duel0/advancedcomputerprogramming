package coda;

public interface ICoda{
    int size();
    boolean empty();
    boolean full();
    void inserisci(String command);
    String preleva();
}