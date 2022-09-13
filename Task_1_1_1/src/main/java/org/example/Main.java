package org.example;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {5, 4, 3, 2, 1};
        heapSort(arr1);
        System.out.println("Test 1: 5 4 3 2 1");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        int[] arr2 = {1, 2, 3, 4};
        heapSort(arr2);
        System.out.println("Test 2: 1 2 3 4");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
        int[] arr3 = {1};
        heapSort(arr3);
        System.out.println("Test 3: 1");
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();
        int[] arr4 = {7, 6, 1, 4, 2, 10, 9, 3, 4};
        heapSort(arr4);
        System.out.println("Test 4: 7 6 1 4 2 10 9 3 4");
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }
    }

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