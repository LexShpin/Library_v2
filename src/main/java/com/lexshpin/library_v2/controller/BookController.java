package com.lexshpin.library_v2.controller;

import com.lexshpin.library_v2.dao.BookDAO;
import com.lexshpin.library_v2.dao.PersonDAO;
import com.lexshpin.library_v2.model.Book;
import com.lexshpin.library_v2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") Book book) {
        bookDAO.add(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        bookDAO.assignBook(id, person.getId());

        return "redirect:/books";
    }

    @PostMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int id) {
        bookDAO.freeBook(id);

        return "redirect:/books";
    }
}
