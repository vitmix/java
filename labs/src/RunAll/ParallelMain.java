package RunAll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParallelMain {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            // running clock in background
            new ThirdLabExecutor("My Animated Clock", null);

            // running rows sorter lab
            FirstLabExecutor t1 = new FirstLabExecutor("Rows Sorting Executor", reader);
            System.out.println(t1.getThread().getName() + " is alive ? " + t1.getThread().isAlive());
            t1.getThread().join();
            System.out.println(t1.getThread().getName() + " is alive ? " + t1.getThread().isAlive());

            // running ip validator lab
            SecondLabExecutor t2 = new SecondLabExecutor("Ip Validation Executor", reader);
            System.out.println(t2.getThread().getName() + " is alive ? " + t2.getThread().isAlive());
            t2.getThread().join();
            System.out.println(t2.getThread().getName() + " is alive ? " + t2.getThread().isAlive());

        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception.");
            e.printStackTrace();
        }
    }
}
