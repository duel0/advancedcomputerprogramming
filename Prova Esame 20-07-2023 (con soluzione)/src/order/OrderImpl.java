package order;

import java.util.Random;

import service.IOrder;

public class OrderImpl implements IOrder{
    private int id;
    private int location;
    private String address;

    // Costruttore privo di parametri, inizializzo id, location e address
    // Uso l'interfaccia che estende Serializable: parametro per invocazione JavaRMI
    public OrderImpl(){
        Random r = new Random();
        id = r.nextInt(100)+1;
        location = r.nextInt(5)+1;
        int addrnum = r.nextInt(7)+4;
        address = addrnum+"th avenue";
    }

    @Override
    public void setID(int id) {
        this.id=id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setLocation(int location) {
        this.location=location;
    }

    @Override
    public int getLocation() {
        return location;
    }

    @Override
    public void setAddress(String address) {
        this.address=address;
    }

    @Override
    public String getAdress() {
        return address;
    }
    

    
}
