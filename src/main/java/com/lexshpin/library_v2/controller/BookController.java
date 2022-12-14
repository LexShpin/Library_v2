package com.lexshpin.library_v2.controller;

import com.lexshpin.library_v2.dao.BookDAO;
import com.lexshpin.library_v2.model.Book;
import com.lexshpin.library_v2.model.Person;
import com.lexshpin.library_v2.services.BooksService;
import com.lexshpin.library_v2.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;

    private final PeopleService peopleService;

    private final BookDAO bookDAO;
    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService, BookDAO bookDAO) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) Integer page, @RequestParam(value = "books_per_page", required = false) Integer booksPerPage, @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {

        if (page == null || booksPerPage == null) {
            if (sortByYear) {
                model.addAttribute("books", booksService.findAll(Sort.by("year")));
            } else {
                model.addAttribute("books", booksService.findAll());
            }
        } else {
            if (sortByYear) {
                model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))));
            } else {
                model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage)));
            }
        }


        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") Book book) {
        booksService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        booksService.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        bookDAO.assignBook(id, person.getId());

        return "redirect:/books";
    }

    @PostMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int id) {
        bookDAO.freeBook(id);

        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String query, Model model) {
        if (query == null) {
            model.addAttribute("books", null);
        } else {
            model.addAttribute("books", booksService.findByTitleStartingWith(query));
        }

        return "books/search";
    }

}
