package ru.nsu.aramazanova1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class NoteBook {
    private final List<Note> notebook = new ArrayList<>();

    public NoteBook() {
    }

    public void addNote(String textNote){
        Note newNote = new Note(textNote);
        notebook.add(newNote);
    }

    public void removeNote(String textNote){
        List<Note> a = notebook.stream().filter(x -> x.getText().equals(textNote)).toList();
        for (Note note : a) {
            notebook.remove(note);
        }
    }

    public void showNotes(){
        notebook.sort(Comparator.comparing(Note::getDate));
        notebook.forEach(x -> System.out.println(x.getDate() + " " + x.getText()));
    }

    public void showNotes(String firstDate, String lastDate, List<String> keyWords){
        List<Note> showing = new ArrayList<>();
        for(String keyWord : keyWords){
            notebook.stream().filter(x -> x.getText().contains(keyWord)).forEach(showing::add);
        }
        showing.sort(Comparator.comparing(Note::getDate));
    }

}
