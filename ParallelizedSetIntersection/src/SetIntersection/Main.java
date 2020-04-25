package SetIntersection;

import java.util.Arrays;
import java.util.Random;

import static SetIntersection.Utilities.makeRandomArray;
import static SetIntersection.Utilities.sequentialMergeSort;

public class Main {

    public static void performanceTest(int capacity) {
        int[] sData = new Random().ints(capacity).toArray();
        int[] pData = Arrays.copyOf(sData, sData.length);

        long startTime = System.nanoTime();
        sequentialMergeSort(sData);
        long endTime = System.nanoTime();

        System.out.println("\nElapsed time of s MS : " + (endTime - startTime) / 1000000 + " ms");

        ParallelMergeSort sorter = new ParallelMergeSort(pData);
        startTime = System.nanoTime();
        sorter.sort();
        endTime = System.nanoTime();

        System.out.println("\nElapsed time of p MS : " + (endTime - startTime) / 1000000 + " ms");
    }

    public static void testSetIntersection(int capacity) {
        FileManager fMan = new FileManager("D:\\semester 8\\DataForSet\\in_data.txt",
                "D:\\semester 8\\DataForSet\\out_data.txt");

        long startTime, endTime;

        int[] data = fMan.read(capacity);
        SetIntersector intersector = new SetIntersector(data);

        startTime = System.nanoTime();
        int[] sRes = intersector.getIntersection(false);
        endTime = System.nanoTime();
        System.out.println("\nElapsed time of seq intersection : " + (endTime - startTime) / 1000000 + " ms");
        fMan.write(sRes);

        startTime = System.nanoTime();
        int[] pRes = intersector.getIntersection(true);
        endTime = System.nanoTime();
        System.out.println("\nElapsed time of par intersection : " + (endTime - startTime) / 1000000 + " ms");
        fMan.write(pRes);
    }

    public static void main(String[] args) {
        testSetIntersection(1000000);
    }
}
