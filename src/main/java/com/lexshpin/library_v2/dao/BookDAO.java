package com.lexshpin.library_v2.dao;

import com.lexshpin.library_v2.model.Book;
import com.lexshpin.library_v2.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Book> books = session.createQuery("select p from Book p", Book.class).getResultList();

        return books;
    }

    @Transactional
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Book.class, id);
    }

    @Transactional
    public void add(Book book) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Session session = sessionFactory.unwrap(Session.class);

        Book book = session.get(Book.class, id);

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setYear(updatedBook.getYear());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);

        session.remove(book);
    }

    @Transactional
    public void assignBook(int bookId, int personId) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, bookId);

        book.setAssignedTo(personId);

        session.persist(book);
    }

    @Transactional
    public void freeBook(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);

        book.setAssignedTo(null);

        session.persist(book);
    }
}
