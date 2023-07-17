package magazzino;

import coda.CodaImpl;
import coda.CodaWrapperSem;
import service.ICoda;
import service.IMagazzino;

public class Magazzino {
    public static void main(String[] args) {
        ICoda smartphones = new CodaImpl(5);
        ICoda laptops = new CodaImpl(5);
        ICoda wrapper_s= new CodaWrapperSem(smartphones);
        ICoda wrapper_l= new CodaWrapperSem(laptops);
        IMagazzino magazzino = new MagazzinoImpl(wrapper_l, wrapper_s);
        MagazzinoSkeleton skeleton = new MagazzinoSkeleton(magazzino);
        System.out.println("[MAGAZZINO-SERVER] Server avviato...");
        skeleton.runSkeleton();
        
    }
}

