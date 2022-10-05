package ru.nsu.aramazanova;

/**
 * sorting array.
 */
public class HeapSort {

    /**
     * sorting array.
     *
     * @param arr array
     */
    public static void heapSort(int[] arr) {
        int size = arr.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            sort(arr, i, size);
        }
        for (int i = size - 1; i >= 0; i--) {
            int b = arr[i];
            arr[i] = arr[0];
            arr[0] = b;
            sort(arr, 0, i);
        }
    }

    /**
     * search max element and put it in root of subtree.
     *
     * @param arr array
     * @param i root index of subtree
     * @param size size of array
     */
    public static void sort(int[] arr, int i, int size) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int idx = i;
        if (l < size && arr[l] > arr[idx]) {
            idx = l;
        }
        if (r < size && arr[r] > arr[idx]) {
            idx = r;
        }
        if (i != idx) {
            int b = arr[i];
            arr[i] = arr[idx];
            arr[idx] = b;
            sort(arr, idx, size);
        }
    }
}