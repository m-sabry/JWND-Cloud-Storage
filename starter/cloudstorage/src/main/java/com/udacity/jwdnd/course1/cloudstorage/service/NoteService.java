package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Note getOne(int id) {
        return  noteMapper.find(id);
    }

    public int create(Note note) {
        return noteMapper.create(note);
    }

    public void update(Note note){
        noteMapper.update(note);
    }

    public void delete(int id) {
        noteMapper.deleteNote(id);
    }

    public List<Note> getUserNotes(int userId) {
        return noteMapper.findByUser(userId);
    }
}
