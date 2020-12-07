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

    @GetMapping("")
    public String getAll(Model model){
        model.addAttribute("notes", noteService.getAll());
        return "home";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") int id, Model model){
        model.addAttribute("notes", noteService.getOne(id));
        return "home";
    }

    @PostMapping("")
    public String create(Authentication authentication, @ModelAttribute Note note, Model model){
        User currentUser = (User) authentication.getPrincipal();
        note.setUserId(currentUser.getUserId());
        noteService.create(note);
        model.addAttribute("notes", noteService.getAll());
        return "home";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model){
        return "home";
    }

    @PostMapping("/{id}")
    public String deleteNote(@PathVariable("id") int id, Model model){
        noteService.delete(id);
        return "home";
    }
}
