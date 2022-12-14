package ru.nsu.aramazanova1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing grade book.
 */
public class StudentGradeBookTest {
    @Test
    public void myGradeBookTest() throws IOException {
        try (BufferedReader file =
                     new BufferedReader(new FileReader(
                             "src/test/resources/myGradeBook.txt", StandardCharsets.UTF_8))) {
            StudentGradeBook a = new StudentGradeBook();
            a.createGradeBook(file);
            Assertions.assertEquals("Обычная", a.getScholarship(1));
            Assertions.assertEquals("Стипендии нет(", a.getScholarship(2));
            Assertions.assertEquals("Обычный диплом", a.getTypeOfDiploma());
            Assertions.assertEquals("4.1", a.getAverage());
        }

    }

    @Test
    public void redDiplomaTest() throws IOException {
        try (BufferedReader file =
                     new BufferedReader(new FileReader(
                             "src/test/resources/redDiploma.txt", StandardCharsets.UTF_8))) {
            StudentGradeBook a = new StudentGradeBook();
            a.createGradeBook(file);
            Assertions.assertEquals("Красный диплом", a.getTypeOfDiploma());
            a.addMark("предмет 1", "удовл.", 3);
            Assertions.assertEquals("Обычный диплом", a.getTypeOfDiploma());
        }
    }

    @Test
    public void scholarshipTest() throws IOException {
        try (BufferedReader file =
                     new BufferedReader(new FileReader(
                             "src/test/resources/redDiploma.txt", StandardCharsets.UTF_8))) {
            StudentGradeBook a = new StudentGradeBook();
            a.createGradeBook(file);
            Assertions.assertEquals("Обычная", a.getScholarship(1));
            Assertions.assertEquals("Повышенная, но есть одна четверка", a.getScholarship(2));
            a.addMark("новый предмет 1", "хор.", 2);
            Assertions.assertEquals("Обычная", a.getScholarship(2));
            a.addMark("новый предмет 2", "удовл.", 2);
            Assertions.assertEquals("Стипендии нет(", a.getScholarship(2));
        }
    }

    @Test
    public void averageTest() throws IOException {
        try (BufferedReader file =
                     new BufferedReader(new FileReader(
                             "src/test/resources/myGradeBook.txt", StandardCharsets.UTF_8))) {
            StudentGradeBook a = new StudentGradeBook();
            a.createGradeBook(file);
            Assertions.assertEquals("4.1", a.getAverage());
            a.addMark("новый предмет 1", "хор.", 1);
            a.addMark("новый предмет 2", "удовл.", 2);
            Assertions.assertEquals("4.0", a.getAverage());
        }
    }

}