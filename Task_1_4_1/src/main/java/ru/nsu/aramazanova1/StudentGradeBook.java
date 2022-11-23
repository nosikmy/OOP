package ru.nsu.aramazanova1;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class StudentGradeBook {

    private final Map<String, markLastSemester> gradeBook;
    private double gpa;
    private boolean scholarship;
    private boolean diploma;
    record markLastSemester(Integer mark, Integer LastSemester) {};

    public StudentGradeBook() {
        this.gradeBook = new HashMap<>();
        this.scholarship = true;
    }

    public void createGradeBook(String d, Integer currentSemester) throws IOException {
        BufferedReader scanner = new BufferedReader(new FileReader(d, StandardCharsets.UTF_8));
        double sumGrade = 0;
        int countGrade = 0;
        int semester = 0;
        boolean oneFour = true;
        boolean flag = false;
        String s = scanner.readLine();
        while (s != null){
            String[] words = s.split(" ");
            if(words[0].equals("Успеваемость")){
                semester = words[2].charAt(0);
                flag = semester == currentSemester;
            }
            else{
                String subject = null;
                int n = words.length;
                for(int i = 0; i < n - 1; i++){
                    subject = subject + ' ' + words[i];
                }
                countGrade++;
                switch (words[n - 1]) {
                    case "отл." -> {
                        markLastSemester input = new markLastSemester(5, semester);
                        gradeBook.put(s, input);
                        sumGrade += 5;
                    }
                    case "хор." -> {
                        markLastSemester input = new markLastSemester(4, semester);
                        gradeBook.put(s, input);
                        sumGrade += 4;
                        if (!oneFour && flag) {
                            scholarship = false;
                        } else if (flag) {
                            oneFour = false;
                        }
                    }
                    case "удвл." -> {
                        markLastSemester input = new markLastSemester(3, semester);
                        gradeBook.put(s, input);
                        sumGrade += 3;
                        if (flag) {
                            oneFour = false;
                        }
                    }
                    case "зачет" -> {
                        countGrade--;
                        markLastSemester input = new markLastSemester(1, semester);
                        gradeBook.put(s, input);
                    }
                }
            }
            s = scanner.readLine();
        }
        gpa = sumGrade / countGrade;
    }

//    public void defineDiploma(String s, Integer currentSemester) throws FileNotFoundException {
//        createGradeBook(s, currentSemester);
//    }

}
