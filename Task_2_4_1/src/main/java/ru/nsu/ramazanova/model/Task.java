package ru.nsu.ramazanova.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

@Data
@AllArgsConstructor
public class Task {
    private int id;
    private String name;
    private double points;
}
