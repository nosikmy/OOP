package ru.nsu.aramazanova1;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the function for finding entries of a substring in a string.
 */
public class SubstringTest {
    @Test
    public void exampleTest1() throws IOException {
        List<Pair> ans = new ArrayList<>();
        List<Pair> actual;
        ans.add(new Pair(0, 7));
        try (Reader file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("example1.txt")), UTF_8))) {
            actual = Substring.searchSubstring(file, "пирог");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i), actual.get(i));
        }
    }

    @Test
    public void exampleTest2() throws IOException {
        List<Pair> ans = new ArrayList<>();
        List<Pair> actual;
        try (Reader file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("example2.txt")), UTF_8))) {
            actual = Substring.searchSubstring(file, "пирог");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i), actual.get(i));
        }
    }

    @Test
    public void linesTest() throws IOException {

        List<Pair> ans = new ArrayList<>();
        List<Pair> actual;
        ans.add(new Pair(0, 0));
        ans.add(new Pair(0, 2));
        ans.add(new Pair(1, 4));
        try (Reader file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("2.txt")), UTF_8))) {
            actual = Substring.searchSubstring(file, "баба");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i), actual.get(i));
        }
    }

    @Test
    public void warAndPeaceTest() throws IOException {
        List<Pair> ans = new ArrayList<>();
        List<Pair> actual;
        ans.add(new Pair(1558, 50));
        ans.add(new Pair(22526, 34));
        ans.add(new Pair(22549, 59));
        ans.add(new Pair(22553, 6));
        ans.add(new Pair(22761, 35));
        ans.add(new Pair(26484, 36));
        ans.add(new Pair(40990, 6));
        ans.add(new Pair(44201, 63));
        ans.add(new Pair(44202, 61));
        ans.add(new Pair(44204, 5));
        ans.add(new Pair(44205, 13));
        ans.add(new Pair(63558, 19));
        try (Reader file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("WarAndPeace.txt")), UTF_8))) {
            actual = Substring.searchSubstring(file, "the oak");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i), actual.get(i));
        }
    }

    @Test
    public void emptyFileTest() throws IOException {
        List<Pair> ans = new ArrayList<>();
        List<Pair> actual;
        try (Reader file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("empty.txt")), UTF_8))) {
            actual = Substring.searchSubstring(file, "");
        }
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i).line(), actual.get(i).line());
            Assertions.assertEquals(ans.get(i).entryIndex(), actual.get(i).entryIndex());
        }
    }

    //about 6 minutes
    @Test
    public void bigFileTest() throws IOException, NullPointerException {
        List<Pair> ans = new ArrayList<>();
        List<Pair> actual;
        ans.add(new Pair(15, 156));
        ans.add(new Pair(86, 456));
        ans.add(new Pair(123, 321));
        ans.add(new Pair(4444, 555));
        ans.add(new Pair(6789, 987));
        ans.add(new Pair(9999, 0));
        CreatorBigFile.createBigFile("src/test/resources/big.txt");
        try (Reader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/test/resources/big.txt"), UTF_8))) {
            actual = Substring.searchSubstring(file, "abc");
        }
        Files.delete(Paths.get("src/test/resources/big.txt"));
        Assertions.assertEquals(ans.size(), actual.size());
        for (int i = 0; i < ans.size(); i++) {
            Assertions.assertEquals(ans.get(i), actual.get(i));
        }
    }
}

