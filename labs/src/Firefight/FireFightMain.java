package Firefight;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.regex.Pattern;

public class FireFightMain {

    static int countDown = 10;

    static private Exchanger<IpBuffer<StringBuffer>> exchanger = new Exchanger<>();
    static private IpBuffer<StringBuffer> initEmptyBuffer = new IpBuffer<>();
    static private IpBuffer<StringBuffer> initFullBuffer = new IpBuffer<>();

    public static void main(String[] args) {
        System.out.println("Starting...");

        new Thread(new IpFiller()).start();
        new Thread(new IpEmptier()).start();
    }

    public static class IpFiller implements Runnable {

        private void addToBufferIp(IpBuffer<StringBuffer> buffer) {
            // generating random IP's
            Random r = new Random();
            StringBuffer str = new StringBuffer();
            str.append(r.nextInt(256) + "." + r.nextInt(256) + ".");
            str.append(r.nextInt(256) + "." + r.nextInt(256));
            buffer.put(str);
        }

        @Override
        public void run() {
            IpBuffer currentBuffer = initEmptyBuffer;

            try {
                while(currentBuffer != null && countDown > 0) {
                    addToBufferIp(currentBuffer);

                    if (currentBuffer.isFull()) {
                        countDown--;
                        currentBuffer = exchanger.exchange(currentBuffer);
                    }
                }
            } catch(InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }

    public static class IpEmptier implements Runnable {

        private static IpValidator validator = new IpValidator();

        private void takeIpAndValidate(IpBuffer<StringBuffer> buffer) {
            if (!buffer.isEmpty()) {
                validator.validate(buffer.get().toString());
            }
        }

        @Override
        public void run() {
            IpBuffer<StringBuffer> currentBuffer = initFullBuffer;

            try {
                while (currentBuffer != null && countDown > 0) {
                    takeIpAndValidate(currentBuffer);

                    if (currentBuffer.isEmpty()) {
                        currentBuffer = exchanger.exchange(currentBuffer);
                    }
                }
            } catch(InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }

    public static class IpValidator {
        private static final String IPV4_REGEXP = "^(?:(?:^|\\.)(?:2(?:5[0-5]|[0-4]\\d)|1?\\d?\\d)){4}$";
        private static Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEXP);

        public void validate(String ip) {
            if (ip != null) {
                System.out.print("Validating IP : " + ip + ". ");

                if (isValid(ip)) {
                    System.out.println("IP is correct.");
                } else {
                    System.out.println("IP is not correct!");
                }
            }
        }

        private static boolean isValid(final String ip) {
            return IPV4_PATTERN.matcher(ip).matches();
        }
    }
}
