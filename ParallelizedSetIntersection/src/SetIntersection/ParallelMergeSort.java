package SetIntersection;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {

    private static final int THRESHOLD = 128;

    private final int[] data;
    private final int   low;
    private final int   high;

    public ParallelMergeSort(int[] data) {
        this.data = data;
        this.low  = 0;
        this.high = data.length - 1;
    }

    public ParallelMergeSort(int[] data, int low, int high) {
        this.data = data;
        this.low  = low;
        this.high = high;
    }

    public void sort() {
        compute();
    }

    @Override
    protected void compute() {
        if (low < high) {
            int amount = high - low;

            if (amount < THRESHOLD) {
                insertionSort();
            } else {
                int mid = low + Math.floorDiv(amount, 2);

                invokeAll(
                        new ParallelMergeSort(data, low, mid),
                        new ParallelMergeSort(data, mid + 1, high));

                merge(mid);
            }
        }
    }

    private void insertionSort() {
        for (int i = low +1; i <= high; ++i) {
            int curr = data[i];
            int j = i-1;

            while (low <= j && curr < data[j])
                data[j+1] = data[j--];

            data[j+1] = curr;
        }
    }

    private void merge(int mid) {
        int[] left = Arrays.copyOfRange(data, low, mid+1);
        int[] right = Arrays.copyOfRange(data, mid+1, high +1);
        int f = low;

        int li = 0, ri = 0;
        while (li < left.length && ri < right.length) {
            if (left[li] <= right[ri]) {
                data[f++] = left[li++];
            } else {
                data[f++] = right[ri++];
            }
        }

        while (li < left.length) {
            data[f++] = left[li++];
        }

        while (ri < right.length) {
            data[f++] = right[ri++];
        }
    }
}