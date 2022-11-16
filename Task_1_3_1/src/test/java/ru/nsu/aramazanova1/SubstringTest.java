package ru.nsu.aramazanova1;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Tests for the function for finding entries of a substring in a string.
 */
public class SubstringTest {
    @Test
    public void exampleTest1() throws IOException {
        List<Integer[]> ans = new ArrayList<>();
        List<Integer[]> actual;
        ans.add(new Integer[]{0, 7});
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/test/resources/example1.txt"), UTF_8))) {
            actual = Substring.searchSubstring(file, "пирог");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i)[0], actual.get(i)[0]);
            Assertions.assertEquals(ans.get(i)[1], actual.get(i)[1]);
        }
    }

    @Test
    public void exampleTest2() throws IOException {
        List<Integer[]> ans = new ArrayList<>();
        List<Integer[]> actual;
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/test/resources/example2.txt"), UTF_8))) {
            actual = Substring.searchSubstring(file, "пирог");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i)[0], actual.get(i)[0]);
            Assertions.assertEquals(ans.get(i)[1], actual.get(i)[1]);
        }
    }

    @Test
    public void linesTest() throws IOException {

        List<Integer[]> ans = new ArrayList<>();
        List<Integer[]> actual;
        ans.add(new Integer[]{0, 0});
        ans.add(new Integer[]{0, 2});
        ans.add(new Integer[]{1, 4});
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/test/resources/2.txt"), UTF_8))) {
            actual = Substring.searchSubstring(file, "баба");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i)[0], actual.get(i)[0]);
            Assertions.assertEquals(ans.get(i)[1], actual.get(i)[1]);
        }
    }

    @Test
    public void warAndPeaceTest() throws IOException {
        List<Integer[]> ans = new ArrayList<>();
        List<Integer[]> actual;
        ans.add(new Integer[]{1558, 50});
        ans.add(new Integer[]{22526, 34});
        ans.add(new Integer[]{22549, 59});
        ans.add(new Integer[]{22553, 6});
        ans.add(new Integer[]{22761, 35});
        ans.add(new Integer[]{26484, 36});
        ans.add(new Integer[]{40990, 6});
        ans.add(new Integer[]{44201, 63});
        ans.add(new Integer[]{44202, 61});
        ans.add(new Integer[]{44204, 5});
        ans.add(new Integer[]{44205, 13});
        ans.add(new Integer[]{63558, 19});
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/test/resources/WarAndPeace.txt"), UTF_8))) {
            actual = Substring.searchSubstring(file, "the oak");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i)[0], actual.get(i)[0]);
            Assertions.assertEquals(ans.get(i)[1], actual.get(i)[1]);
        }
    }

    @Test
    public void emptyFileTest() throws IOException {
        List<Integer[]> ans = new ArrayList<>();
        List<Integer[]> actual;
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/test/resources/empty.txt"), UTF_8))) {
            actual = Substring.searchSubstring(file, "");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i)[0], actual.get(i)[0]);
            Assertions.assertEquals(ans.get(i)[1], actual.get(i)[1]);
        }
    }

    //about 6 minutes
    @Test
    public void bigFileTest() throws IOException {
        List<Integer[]> ans = new ArrayList<>();
        List<Integer[]> actual;
        ans.add(new Integer[]{15, 156});
        ans.add(new Integer[]{86, 456});
        ans.add(new Integer[]{123, 321});
        ans.add(new Integer[]{4444, 555});
        ans.add(new Integer[]{6789, 987});
        ans.add(new Integer[]{9999, 0});
        CreatorBigFile.createBigFile("src/test/resources/big.txt");
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/test/resources/big.txt"), UTF_8))) {
            actual = Substring.searchSubstring(file, "abc");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i)[0], actual.get(i)[0]);
            Assertions.assertEquals(ans.get(i)[1], actual.get(i)[1]);
        }
    }
}
