package ru.nsu.aramazanova1;

import java.util.Date;

public class Note {
    private Date date;
    private String text;

    public Note(String text) {
        this.date = new Date();
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
}
