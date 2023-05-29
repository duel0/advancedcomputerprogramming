package threadpipe;

import java.io.PipedOutputStream;

public class Test {
    public static void main(String[] args) {
        PipedOutputStream pipeOut = new PipedOutputStream();

        WriterThread w = new WriterThread(pipeOut);
        ReaderThread r = new ReaderThread(pipeOut);

        w.start();
        r.start();
    }
}
