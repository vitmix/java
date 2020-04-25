package Symphony;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;

public class Symphonier {

    static int countDown = 5;

    private static final Symphonizer sym = new Symphonizer();
    private static Exchanger<ArrayList<String>> exchanger = new Exchanger<>();
    private static ArrayList<String> commonBuffer = new ArrayList<>();
    private static ArrayList<String> validatorBuffer = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Starting...");

        System.out.println("Registering main thread...");
        sym.register();

        System.out.println("Starting validator thread...");
        new Thread(new EmptierOfIP()).start();

        for (int idx = 0; idx < countDown; idx++) {
            System.out.println("Registering thread " + idx);
            sym.register();
            new Thread(new FillerOfIP(idx)).start();
        }

        sym.arriveAndDeregister();
        System.out.println("Done !");
    }

    public static class FillerOfIP implements Runnable {

        private int id;

        FillerOfIP(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            do {
                Random r = new Random();
                commonBuffer.add(r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256));
                sym.arriveAndAwaitAdvance();
            } while(!sym.isTerminated());
            System.out.println("Thread" + id + " succeed");
        }
    }

    public static class EmptierOfIP implements Runnable {

        private static Firefight.FireFightMain.IpValidator validator = new Firefight.FireFightMain.IpValidator();

        private void takeIpAndValidate(ArrayList<String> buffer) {
            for (int i = 0; i < buffer.size(); i++)
                validator.validate(buffer.get(i));

            buffer.clear();
        }

        @Override
        public void run() {
            try {
                while (validatorBuffer != null && !sym.isTerminated()) {
                    System.out.println("Validator got " + validatorBuffer.size() + " IPs");
                    takeIpAndValidate(validatorBuffer);
                    validatorBuffer = exchanger.exchange(validatorBuffer);
                }
            } catch(InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }

    public static class Symphonizer extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int parties) {
            System.out.println("\nAdvance on phase " + phase);
            System.out.println("Passing all generated IPs to validator...");

            try {
                if (commonBuffer != null) {
                    commonBuffer = exchanger.exchange(commonBuffer);
                }
            } catch(InterruptedException exc) {
                System.out.println(exc);
            }
            return phase >= countDown;
        }
    }
}

