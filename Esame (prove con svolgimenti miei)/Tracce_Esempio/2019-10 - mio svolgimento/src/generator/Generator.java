package generator;

public class Generator {
    public static void main(String[] args) {
        try {
            GeneratorThread[] threads = new GeneratorThread[3];

            for(int i=0; i<3; i++){
                GeneratorThread t = new GeneratorThread();
                t.start();
                threads[i]=t;
            }

            for(int i=0; i<3; i++){
                threads[i].join();
            }
        } catch (Exception e) {
        }

    }
}
