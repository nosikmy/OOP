package ru.nsu.aramazanova1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * substring search.
 */
public class Substring {

    /**
     * The function determines the line and the start index of each occurrence of a given substring.
     *
     * @param file           text file
     * @param inputSubstring substring to be searched for
     * @return List of pairs of line and entry index
     * @throws IOException exception in case there are problems with the file
     */
    public static List<Integer[]> searchSubstring(BufferedReader file, String inputSubstring)
            throws IOException {
        byte[] bytesOfString = inputSubstring.getBytes();
        String substring = new String(bytesOfString, StandardCharsets.UTF_8);
        List<Integer[]> answer = new ArrayList<>(new ArrayList<>());
        int c;
        int lengthSubstring = substring.length();
        int globalIdx = 0;
        int idx;
        int line = 0;
        while ((c = file.read()) != -1) {
            int flag = 1;
            if ((char) c == '\n') {
                line++;
                globalIdx = 0;
            } else if (c == (int) substring.charAt(0)) {
                file.mark(lengthSubstring);
                idx = globalIdx + 1;
                for (int i = 1; i < lengthSubstring; i++) {
                    globalIdx++;
                    if ((c = file.read()) != (int) substring.charAt(i)) {
                        flag = 0;
                        break;
                    }

                }
                if (flag == 1) {
                    Integer[] insert = new Integer[]{line, globalIdx - lengthSubstring + 1};
                    answer.add(insert);

                }
                file.reset();
                globalIdx = idx;
            } else {
                globalIdx++;
            }
        }
        file.close();
        return answer;
    }

}
