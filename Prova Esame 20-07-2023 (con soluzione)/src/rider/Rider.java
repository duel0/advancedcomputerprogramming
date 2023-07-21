package rider;

import service.IRider;

public class Rider {
    public static void main(String[] args) {
        if(args.length!=3){
            System.out.println("Parametri mancanti!");
            return;
        }
        IRider r = new RiderImpl(Integer.parseInt(args[0]), args[2]);

        // Skeleton per delega, gli passo l'implementazione e il porto
        RiderSkeleton skeleton = new RiderSkeleton(r, Integer.parseInt(args[1]));
        skeleton.runSkeleton();
    }
}
