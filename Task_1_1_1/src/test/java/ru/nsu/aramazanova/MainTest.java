package ru.nsu.aramazanova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing heapsort.
 */
public class MainTest {

    @Test
    public void test1() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] ans = {1, 2, 3, 4, 5};
        Main.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] ans = {1, 2, 3, 4, 5};
        Main.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void test3() {
        int[] arr = {1};
        int[] ans = {1};
        Main.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void test4() {
        int[] arr = {};
        int[] ans = {};
        Main.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }

    @Test
    public void test5() {
        int[] arr = {5, 4, 6, 9, 8, 2, 4, 13, 5, 1, 17, 19};
        int[] ans = {1, 2, 4, 4, 5, 5, 6, 8, 9, 13, 17, 19};
        Main.heapSort(arr);
        Assertions.assertArrayEquals(ans, arr);
    }
}
