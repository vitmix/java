package Ranger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Select all array elements whose values are in a given range.
 */

public class Ranger {
    private static ArrayList<Integer> data = new ArrayList<>();
    private static int leftBound, rightBound;

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter the number of workers : ");

            int numOfWorkers = Integer.parseInt(reader.readLine());
            ExecutorService executor = Executors.newFixedThreadPool(numOfWorkers);

            System.out.print("Enter left and right bounds of a range : ");

            String[] buffer = reader.readLine().split("\\s");

            leftBound  = Integer.parseInt(buffer[0]);
            rightBound = Integer.parseInt(buffer[1]);

            ArrayList<Future<ArrayList<Integer>>> futures = new ArrayList<>(numOfWorkers);
            ArrayList<Integer> foundedValues = new ArrayList<>();

            System.out.println("Enter a list of numbers assuming that its size is a multiple of : " + numOfWorkers);

            buffer = reader.readLine().split("\\s");
            for (int i = 0; i < buffer.length; i++) {
                data.add(Integer.parseInt(buffer[i]));
            }

            if (data.size() != 0 && data.size() % numOfWorkers == 0) {
                System.out.println("Start executing...");

                int sizeOfSlice = data.size() / numOfWorkers, from, to;

                for (int i = 0; i < numOfWorkers; i++) {
                    from = i * sizeOfSlice;
                    to = from + sizeOfSlice;

                    futures.add(executor.submit(new RangeFinder(from, to)));
                }

                System.out.println("Waiting for workers...");

                for (int i = 0; i < numOfWorkers; i++)
                    foundedValues.addAll(futures.get(i).get());

                System.out.println("Ranger have found " + foundedValues.size() + " values :");

                for (Integer i : foundedValues)
                    System.out.print(i + " ");

                executor.shutdown();
                System.out.println("Done !");
            } else {
                System.out.println("Size of data is not a multiple of " + numOfWorkers + "! Bye!");
            }
        } catch(InterruptedException | ExecutionException | IOException exc) {
            System.out.println(exc);
        }
    }

    public static class RangeFinder implements Callable<ArrayList<Integer>> {
        private int from, to;

        RangeFinder(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public ArrayList<Integer> call() {
            int value = 0;
            ArrayList<Integer> slice = new ArrayList<>(to - from);

            for (int i = from; i < to; i++) {
                value = data.get(i);

                if (leftBound <= value && value <= rightBound)
                    slice.add(value);
            }

            return slice;
        }
    }

}