package server;

public class Server {
    public static void main(String[] args) {
        CounterImpl count = new CounterImpl();
        count.runSkeleton();
    }
}
