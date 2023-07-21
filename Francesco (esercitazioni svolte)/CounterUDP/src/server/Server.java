package server;

public class Server {
    public static void main(String[] args) {
        CounterImpl counter = new CounterImpl();
        counter.runSkeleton();
    }
}
