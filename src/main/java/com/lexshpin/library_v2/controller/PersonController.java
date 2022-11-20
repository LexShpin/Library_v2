package com.lexshpin.library_v2.controller;

import com.lexshpin.library_v2.dao.PersonDAO;
import com.lexshpin.library_v2.model.Person;
import com.lexshpin.library_v2.services.BooksService;
import com.lexshpin.library_v2.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PeopleService peopleService;
    private final BooksService booksService;
    private final PersonDAO personDAO;

    public PersonController(PeopleService peopleService, BooksService booksService, PersonDAO personDAO) {
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", personDAO.getBooksByPerson(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") Person person) {
        peopleService.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        peopleService.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);

        return "redirect:/people";
    }
}
