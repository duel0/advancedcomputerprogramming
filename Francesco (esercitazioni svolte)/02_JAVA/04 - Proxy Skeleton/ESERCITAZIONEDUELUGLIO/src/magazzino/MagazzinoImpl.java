package magazzino;

import service.ICoda;
import service.IMagazzino;

public class MagazzinoImpl implements IMagazzino{
    private ICoda laptops;
    private ICoda smartphones;

    public MagazzinoImpl(ICoda l, ICoda s){
        laptops=l;
        smartphones=s;
    }

    @Override
    public void deposita(String articolo, int id) {
        if(articolo.equalsIgnoreCase("laptop")){
            laptops.deposita(id);
            System.out.println("[MAGAZZINO] Deposito laptop con ID: "+id);
        } else {
            smartphones.deposita(id);
            System.out.println("[MAGAZZINO] Deposito smartphone con ID: "+id);
        }
    }

    @Override
    public int preleva(String articolo) {
        Integer id = null;
        if(articolo.equalsIgnoreCase("laptop")){
            id = new Integer(laptops.preleva());
            System.out.println("[MAGAZZINO] Prelevo laptop con ID: "+id);
        } else {
            id = new Integer(smartphones.preleva());
            System.out.println("[MAGAZZINO] Prelevo smartphone con ID: "+id);
        }
        return id;
    }
    
}
