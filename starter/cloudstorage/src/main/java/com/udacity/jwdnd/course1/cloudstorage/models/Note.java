package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private int noteId;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
