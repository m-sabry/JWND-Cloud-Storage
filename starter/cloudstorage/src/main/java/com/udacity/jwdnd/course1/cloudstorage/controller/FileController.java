package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("files")
public class FileController {

    @GetMapping("")
    public String getAll(Model model){
        return "home";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") int id, Model model){
        return "home";
    }

    @PostMapping("")
    public String create(Model model){
        return "home";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model){
        return "home";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        return "home";
    }
}
