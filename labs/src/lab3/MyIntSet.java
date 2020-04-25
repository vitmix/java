package lab3;

import java.util.*;

/**
 * Define a Set class based on the set of integers, n = size.
 * Create methods for obtaining the intersection and union of sets.
 */

public class MyIntSet {

    public static void main(String[] args) {
        System.out.println("Enter the size of sets : ");
        Scanner in = new Scanner(System.in);

        int setSize = in.nextInt();
        MySet setA = new MySet(setSize);
        MySet setB = new MySet(setSize);

        setA.add(1);
        setA.add(2);
        setA.add(2);
        setA.add(3);

        setB.add(2);
        setB.add(5);
        setB.add(6);
        setB.add(7);
        setB.add(1);
        setB.add(8);

        Set<Integer> unionRes = setA.union(setB);
        Set<Integer> interRes = setA.intersect(setB);

        System.out.println("Result of union : ");

        for (Integer value : unionRes) {
            System.out.print(value + " ");
        }

        System.out.println("\nResult of intersection : ");

        for (Integer value : interRes) {
            System.out.print(value + " ");
        }
    }
}

