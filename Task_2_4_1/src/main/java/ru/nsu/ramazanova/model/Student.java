package ru.nsu.ramazanova.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URL;

@Data
@AllArgsConstructor
public class Student {
    private String nickname;
    private String fullName;
    private String repositoryURL;
}
