package magazzino;

import coda.CodaImpl;
import coda.CodaWrapperSync;
import service.ICoda;

public class MagazzinoImpl extends MagazzinoSkeletonE{

    private ICoda laptop;
    private ICoda smartphone;

    public MagazzinoImpl(){
        ICoda l = new CodaImpl(5);
        ICoda s = new CodaImpl(5);
        laptop = new CodaWrapperSync(l);
        smartphone = new CodaWrapperSync(s);
    }

    @Override
    public void deposita(String articolo, int id) {
        if(articolo.equals("laptop")){
            System.out.println("[MAGAZZINO] Inserisco il laptop "+id);
            laptop.inserisci(id);
        } else if (articolo.equals("smartphone")){
            System.out.println("[MAGAZZINO] Inserisco lo smartphone "+id);
            smartphone.inserisci(id);
        }
    }

    @Override
    public int preleva(String articolo) {
        int x=0;
        if(articolo.equals("laptop")){
            x=laptop.preleva();
            System.out.println("[MAGAZZINO] Prelevo il laptop "+x);
        } else if (articolo.equals("smartphone")){
            x=smartphone.preleva();
            System.out.println("[MAGAZZINO] Prelevo lo smartphone "+x);
        }
        return x;
    }
    
}
