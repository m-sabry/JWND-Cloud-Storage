package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("notes")
public class NoteController {
    private @Autowired NoteService noteService;

    @GetMapping()
    public String getUserNotes(Authentication authentication, Model model){
        User currentUser = (User) authentication.getPrincipal();
        model.addAttribute("notes", noteService.getUserNotes(currentUser.getUserId()));
        return "redirect:/";
    }


    @PostMapping("")
    public String create(Authentication authentication, @ModelAttribute Note note, Model model){
        User currentUser = (User) authentication.getPrincipal();
        note.setUserId(currentUser.getUserId());

        if(note.getNoteId() != null ) noteService.update(note);
        else noteService.create(note);

        model.addAttribute("notes", noteService.getUserNotes(currentUser.getUserId()));
        return "redirect:/";
    }
//
//    @PutMapping("")
//    public String update(Authentication authentication, Note note,Model model){
//
//        noteService.update(note);
//
//        User currentUser = (User) authentication.getPrincipal();
//        model.addAttribute("notes", noteService.getUserNotes(currentUser.getUserId()));
//        return "redirect:/";
//    }

    @GetMapping("delete-note")
    public String deleteNote(@RequestParam("noteId") int noteId){

        noteService.delete(noteId);
        return "redirect:/";
    }
}
