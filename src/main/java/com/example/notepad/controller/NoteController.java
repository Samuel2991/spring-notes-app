package com.example.notepad.controller;

import com.example.notepad.model.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NoteController {

    private List<Note> notes = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("notes", notes);
        return "index";
    }

    @PostMapping("/save")
    public String saveNote(@ModelAttribute Note note) {
        notes.add(note);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable int id) {
        if (id >= 0 && id < notes.size()) {
            notes.remove(id);
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable int id, Model model) {
        model.addAttribute("note", notes.get(id));
        model.addAttribute("editId", id);
        model.addAttribute("notes", notes);
        return "index";
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable int id, @ModelAttribute Note note) {
        notes.set(id, note);
        return "redirect:/";
    }
}