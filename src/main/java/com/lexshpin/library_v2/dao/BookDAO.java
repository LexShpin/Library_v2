package com.lexshpin.library_v2.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Book> index() {
//        Session session = sessionFactory.getCurrentSession();
//
//        List<Book> books = session.createQuery("select p from Book p", Book.class).getResultList();
//
//        return books;
//    }
//
//    @Transactional
//    public Book show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Book.class, id);
//    }
//
//    @Transactional
//    public void add(Book book) {
//        Session session = sessionFactory.getCurrentSession();
//
//        session.persist(book);
//    }
//
//    @Transactional
//    public void update(int id, Book updatedBook) {
//        Session session = sessionFactory.unwrap(Session.class);
//
//        Book book = session.get(Book.class, id);
//
//        book.setTitle(updatedBook.getTitle());
//        book.setAuthor(updatedBook.getAuthor());
//        book.setYear(updatedBook.getYear());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        Book book = session.get(Book.class, id);
//
//        session.remove(book);
//    }

    public void assignBook(int bookId, int personId) {
        jdbcTemplate.update("update book set assigned_to=? where id=?", personId, bookId);
    }

    public void freeBook(int id) {
        jdbcTemplate.update("update book set assigned_to=NULL where id=?", id);
    }
}
