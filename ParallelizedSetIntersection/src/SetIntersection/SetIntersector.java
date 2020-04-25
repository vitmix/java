package SetIntersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static SetIntersection.Utilities.sequentialMergeSort;

public class SetIntersector {
    private int[] leftChunk, rightChunk;

    public SetIntersector(int[] data) {
        int half = data.length / 2;
        this.leftChunk  = Arrays.copyOfRange(data, 0, half);
        this.rightChunk = Arrays.copyOfRange(data, half, data.length);
    }

    public void setData(int[] data) {
        int half = data.length / 2;
        this.leftChunk  = Arrays.copyOfRange(data, 0, half);
        this.rightChunk = Arrays.copyOfRange(data, half, data.length);
    }

    private int[] makeIntersection() {
        int left = 0, right = 0, prev = -1;
        ArrayList<Integer> interList = new ArrayList<Integer>();

        while (left < leftChunk.length && right < rightChunk.length) {
            if (leftChunk[left] < rightChunk[right])
                left++;
            else if (rightChunk[right] < leftChunk[left])
                right++;
            else {
                if (prev != rightChunk[right]) {
                    interList.add(rightChunk[right]);
                    prev = rightChunk[right];
                }
                right++;
                left++;
            }
        }

        // int prev = -1, temp = 0;
        int[] result = new int[interList.size()];
        Iterator<Integer> iter = interList.iterator();

        for (int i = 0; i < result.length; i++)
            result[i] = iter.next().intValue();

        return result;
    }

    public int[] getIntersection(boolean runParallel) {
        if (runParallel) {
            ParallelMergeSort sorter1 = new ParallelMergeSort(leftChunk);
            ParallelMergeSort sorter2 = new ParallelMergeSort(rightChunk);
            sorter1.sort();
            sorter2.sort();
        } else {
            sequentialMergeSort(leftChunk);
            sequentialMergeSort(rightChunk);
        }

        return makeIntersection();
    }
}
