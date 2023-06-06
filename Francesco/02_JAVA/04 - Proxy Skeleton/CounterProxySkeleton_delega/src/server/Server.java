package server;

public class Server {
    public static void main(String[] args) {
        CounterImpl count = new CounterImpl();

        CounterSkeletonDelega skeletonDelega = new CounterSkeletonDelega(count);
        skeletonDelega.runSkeleton();
    }
}
