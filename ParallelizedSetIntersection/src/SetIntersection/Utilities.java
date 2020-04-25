package SetIntersection;

import java.util.Random;

public interface Utilities {
    static int[] makeRandomArray(final int capacity) {
        final int[] data = new int[capacity];

        for (int i = 0; i < capacity; i++)
            data[i] = new Random().nextInt(capacity);

        return data;
    }

    static void merge(final int[] data, final int[] temp, final int low, final int mid, final int high) {
        for (int i = low; i <= high; i++)
            temp[i] = data[i];

        int tmpLeft = low, tmpRight = mid + 1, curr = low;

        while (tmpLeft <= mid && tmpRight <= high) {
            if (temp[tmpLeft] <= temp[tmpRight]) {
                data[curr] = temp[tmpLeft++];
            } else {
                data[curr] = temp[tmpRight++];
            }
            curr++;
        }

        while (tmpLeft <= mid) {
            data[curr++] = temp[tmpLeft++];
        }

        while (tmpRight <= high) {
            data[curr++] = temp[tmpRight++];
        }
    }

    static void sequentialMergeSort(final int[] data) {
        final int[] temp = new int[data.length];
        sequentialMergeSort(data, temp, 0, data.length - 1);
    }

    static void sequentialMergeSort(final int[] data, final int[] temp, final int low, final int high) {
        if (low < high) {
            final int mid = (low + high) / 2;
            sequentialMergeSort(data, temp, low, mid);
            sequentialMergeSort(data, temp, mid + 1, high);
            merge(data, temp, low, mid, high);
        }
    }
}
