package ru.nsu.aramazanova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing heapsort.
 */
public class HeapSortTest {

    @Test
    public void testReversedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] ans = {1, 2, 3, 4, 5};
        HeapSort.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] ans = {1, 2, 3, 4, 5};
        HeapSort.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void testOneElementArray() {
        int[] arr = {1};
        int[] ans = {1};
        HeapSort.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int[] ans = {};
        HeapSort.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void testRandomArray() {
        int[] arr = {5, 4, 6, 9, 8, 2, 4, 13, 5, 1, 17, 19};
        int[] ans = {1, 2, 4, 4, 5, 5, 6, 8, 9, 13, 17, 19};
        HeapSort.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }
}
