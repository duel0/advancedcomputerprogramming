package client;

import service.ICounter;

public class Client {
    public static void main(String[] args) {
        String host = args[0];
        String operation = args[1];
        System.out.println("HOST: "+host);
        System.out.println("operation: "+operation);
        if (!operation.equals("sum")&&
	    	!operation.equals("get")&&
	    	!operation.equals("sqr")&&
	    	!operation.equals("inc")) {
	    	
	    		System.out.println("Comando ERRATO! \n Comandi accettati: sum <value>, get, sqr, inc");
	    		
	    } else {
            ICounter counter = new CounterProxy(host, 2500);
            if(operation.equals("sum")){
                try{
                    int value = Integer.parseInt(args[2]);
                    counter.sum(value);
                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
            } else if (operation.equals("get")){
                System.out.println("Valore letto: "+counter.get());
            } else if (operation.equals("sqr")){
                counter.square();
            } else if (operation.equals("inc")){
                counter.inc();
            }
        }
    }
}
