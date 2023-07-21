package generator;

import java.util.Random;

public class Reading implements IReading{
    private String tipo;
    private int valore;
    private final static long serialVersionUID=10;
    public Reading(){
        Random r = new Random();
        valore = r.nextInt(50);
        if(r.nextInt(100)%2==0){
            tipo="temperatura";
        } else {
            tipo="pressione";
        }
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getValore() {
        return valore;
    }
    public void setValore(int valore) {
        this.valore = valore;
    }

    
    
}
