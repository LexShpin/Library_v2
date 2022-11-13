package com.lexshpin.library_v2.controller;

import com.lexshpin.library_v2.dao.PersonDAO;
import com.lexshpin.library_v2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") Person person) {
        personDAO.add(person);

        return "redirect:/people";
    }
}
