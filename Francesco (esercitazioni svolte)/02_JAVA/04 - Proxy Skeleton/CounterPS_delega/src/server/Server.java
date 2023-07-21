package server;

public class Server {
    public static void main(String[] args) {
        CounterImpl c = new CounterImpl();
        CounterSkeletonDelega cs = new CounterSkeletonDelega(c);
        cs.runSkeleton();

    }
}
