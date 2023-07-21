package service;

import java.io.Serializable;

public interface IOrder extends Serializable{
    void setID(int id);
    int getID();
    void setLocation(int location);
    int getLocation();
    void setAddress(String address);
    String getAdress();
} 