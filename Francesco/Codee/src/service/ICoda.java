package service;

public interface ICoda{
    int size();
    boolean full();
    boolean empty();
    void inserisci(int i);
    int preleva();
}