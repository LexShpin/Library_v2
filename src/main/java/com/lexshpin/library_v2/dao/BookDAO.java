package com.lexshpin.library_v2.dao;

import com.lexshpin.library_v2.model.Book;
import com.lexshpin.library_v2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?", book.getTitle(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assignBook(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET given_to=? WHERE id=?", personId, bookId);
        Book book = show(bookId);
        book.setAssigned(true);
        System.out.println("Book " + book.getTitle() + " is now assigned");
    }

    public void freeBook(int id) {
        jdbcTemplate.update("UPDATE Book SET given_to=null WHERE id=?", id);
        Book book = show(id);
        book.setAssigned(false);
    }
}
