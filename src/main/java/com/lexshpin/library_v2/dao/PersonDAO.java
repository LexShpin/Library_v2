package com.lexshpin.library_v2.dao;

import com.lexshpin.library_v2.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("select p from Person p", Person.class).getResultList();
//    }
//
//    @Transactional
//    public Person show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public void add(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//
//        session.persist(person);
//    }
//
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//
//        Person person = session.get(Person.class, id);
//
//        person.setName(updatedPerson.getName());
//        person.setYearOfBirth(updatedPerson.getYearOfBirth());
//
//        session.persist(person);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        Person person = session.get(Person.class, id);
//
//        session.remove(person);
//    }

    public List<Book> getBooksByPerson(int id) {
        return jdbcTemplate.query("select * from Book where assigned_to=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
