package ru.nsu.aramazanova1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * creates a big file.
 */
public class CreatorBigFile {

    /**
     * creates a 10 GB file.
     *
     * @param dir file directory
     */
    public static void createBigFile(String dir) {
        try (BufferedWriter file = new BufferedWriter(new FileWriter(dir))) {
            for (int i = 0; i < 1000000; i++) {
                for (int j = 0; j < 10000; j++) {
                    if (i == 15 && j == 156) {
                        file.write("abc");
                        j += 2;
                    }
                    if (i == 86 && j == 456) {
                        file.write("abc");
                        j += 2;
                    }
                    if (i == 123 && j == 321) {
                        file.write("abc");
                        j += 2;
                    }
                    if (i == 4444 && j == 555) {
                        file.write("abc");
                        j += 2;
                    }
                    if (i == 6789 && j == 987) {
                        file.write("abc");
                        j += 2;
                    }
                    if (i == 9999 && j == 0) {
                        file.write("abc");
                        j += 2;
                    } else {
                        file.write('_');
                    }
                }
                file.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
