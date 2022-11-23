package ru.nsu.aramazanova1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StudentGradeBookTest {
    @Test
    public void test() throws IOException {
        StudentGradeBook a = new StudentGradeBook();
        a.createGradeBook("src/test/resources/myGradeBook.txt", 1);
    }

}
