package ru.nsu.ramazanova.model;

import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ControlPoint {
    private String name;

    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private Date date;

    @SneakyThrows
    public ControlPoint(String name, String date) {
        this.name = name;
        this.date = format.parse(date);
    }
}
