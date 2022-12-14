package ru.nsu.aramazanova1;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Grade book realization.
 */
public class StudentGradeBook {

    private final Map<String, MarkLastSemester> gradeBook;
    private final Map<Integer, String> scholarships;
    private double sumGrade;
    private double countGrade;

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
        while ((s = file.readLine()) != null) {
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
        MarkLastSemester input;
        switch (mark) {
            case FIVE -> {
                input = new MarkLastSemester(5, semester);
                gradeBook.put(String.valueOf(subject), input);
                sumGrade += 5;
            }
            case FOUR -> {
                input = new MarkLastSemester(4, semester);
                sumGrade += 4;
                if (scholarships.get(semester).equals("Повышенная, но есть одна четверка")) {
                    scholarships.put(semester, "Обычная");
                } else if (scholarships.get(semester).equals("Повышенная")) {
                    scholarships.put(semester, "Повышенная, но есть одна четверка");
                }
            }
            case THREE -> {
                input = new MarkLastSemester(3, semester);
                sumGrade += 3;
                scholarships.put(semester, "Стипендии нет(");
            }
            case OFFSET -> {
                countGrade--;
                input = new MarkLastSemester(1, semester);
            }
            default -> {
                throw new IllegalArgumentException("Неправильное название оценки");
            }
        }
        if (!gradeBook.containsKey(String.valueOf(subject))) {
            gradeBook.put(String.valueOf(subject), input);
        } else if (gradeBook.get(String.valueOf(subject)).lastSemester() < semester) {
            gradeBook.put(String.valueOf(subject), input);
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
        for (MarkLastSemester h : gradeBook.values()) {
            if (h.mark() != 1) {
                countOfMarks++;
            }
            if (h.mark() == 5) {
                countOfFive++;
            } else if (h.mark() == 3) {
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
        double average = sumGrade / countGrade * 10;
        average = Math.ceil(average) / 10;
        return Double.toString(average);
    }

}