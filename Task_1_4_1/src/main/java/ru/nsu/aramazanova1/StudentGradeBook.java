package ru.nsu.aramazanova1;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Grade book realization.
 */
public class StudentGradeBook {

    private final Map<String, markLastSemester> gradeBook;
    private final Map<Integer, String> scholarships;
    private double sumGrade;
    private double countGrade;

    record markLastSemester(Integer mark, Integer LastSemester) {
    }

    ;

    /**
     * Constructor of new grade book.
     */
    public StudentGradeBook() {
        this.gradeBook = new HashMap<>();
        this.scholarships = new HashMap<>();
        this.sumGrade = 0;
        this.countGrade = 0;
    }

    /**
     * Function for reading information from a text file into a grade book.
     *
     * @param file file to read
     */
    public void createGradeBook(BufferedReader file) throws IOException {
        String s;
        int currentSemester = 0;
        while (true) {
            if ((s = file.readLine()) == null) {
                break;
            }
            String[] words = s.split(" ");
            if ("Успеваемость".equals(words[0])) {
                currentSemester = words[2].charAt(0) - '0';
                scholarships.put(currentSemester, "Повышенная");
            } else {
                StringBuilder subject = new StringBuilder();
                int n = words.length;
                subject.append(words[0]);
                for (int i = 1; i < n - 1; i++) {
                    subject.append(' ').append(words[i]);
                }
                addMark(String.valueOf(subject), words[n - 1], currentSemester);
            }
        }

    }

    /**
     * Function for adding a new subject in a semester.
     *
     * @param subject  subject name
     * @param m        semester grade
     * @param semester semester number
     */
    public void addMark(String subject, String m, int semester) {
        scholarships.putIfAbsent(semester, "Повышенная");
        countGrade++;
        Marks mark = Marks.getMark(m);
        switch (mark) {
            case FIVE -> {
                markLastSemester input = new markLastSemester(5, semester);
                gradeBook.put(String.valueOf(subject), input);
                sumGrade += 5;
            }
            case FOUR -> {
                markLastSemester input = new markLastSemester(4, semester);
                gradeBook.put(String.valueOf(subject), input);
                sumGrade += 4;
                if (scholarships.get(semester).equals("Повышенная, но есть одна четверка")) {
                    scholarships.put(semester, "Обычная");
                } else if (scholarships.get(semester).equals("Повышенная")) {
                    scholarships.put(semester, "Повышенная, но есть одна четверка");
                }
            }
            case THREE -> {
                markLastSemester input = new markLastSemester(3, semester);
                gradeBook.put(String.valueOf(subject), input);
                sumGrade += 3;
                scholarships.put(semester, "Стипендии нет(");
            }
            case OFFSET -> {
                countGrade--;
                markLastSemester input = new markLastSemester(1, semester);
                gradeBook.put(String.valueOf(subject), input);
            }
        }
    }

    /**
     * Function for checking the possibility of getting a red diploma.
     *
     * @return diploma type at the moment
     */
    public String getTypeOfDiploma() {
        double countOfFive = 0;
        double countOfMarks = 0;
        for (markLastSemester h : gradeBook.values()) {
            if (h.mark != 1) {
                countOfMarks++;
            }
            if (h.mark == 5) {
                countOfFive++;
            } else if (h.mark == 3) {
                return "Обычный диплом";
            }
        }
        if (countOfFive / countOfMarks * 100 >= 75.0) {
            return "Красный диплом";
        } else {
            return "Обычный диплом";
        }
    }

    /**
     * Function for getting the type of scholarship in a given semester.
     *
     * @param semester the semester for which the scholarship is paid
     * @return type of scholarship
     */
    public String getScholarship(int semester) {
        return scholarships.get(semester);
    }

    /**
     * Counts the average score.
     *
     * @return average score
     */
    public String getAverage() {
        double average = sumGrade / countGrade;
        return String.format("%.1f", average);
    }

}