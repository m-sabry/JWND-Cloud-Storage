package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final NoteService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;

    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping("/")
    public String home(Authentication authentication, Model model){
        User currentUser = (User) authentication.getPrincipal();

        model.addAttribute("notes", noteService.getUserNotes(currentUser.getUserId()));
        model.addAttribute("files", fileService.getUserFiles(currentUser.getUserId()));
        model.addAttribute("credentials", credentialService.getUserCredentials(currentUser.getUserId()));
        return "home";
    }
}
