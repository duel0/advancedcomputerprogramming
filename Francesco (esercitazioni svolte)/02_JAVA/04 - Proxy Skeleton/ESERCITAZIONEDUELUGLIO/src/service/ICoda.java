package service;

public interface ICoda{
    int size();
    boolean full();
    boolean empty();
    void deposita(int id);
    int preleva();
}