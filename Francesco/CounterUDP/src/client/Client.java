package client;

public class Client {
    public static void main(String[] args) {
        if(args.length==0){
            System.out.println("Mi dai le info? Grz 1000!");
        }
        CounterProxy proxy = new CounterProxy();
        if(args[0].equals("inc")){
            proxy.inc();
        } else if (args[0].equals("sum")){
            int val = Integer.parseInt(args[1]);
            proxy.sum(val);
        } else if(args[0].equals("get")){
            System.out.println("Valore: "+proxy.get());
        } else {
            proxy.square();
        }
    }
}
