package com.lexshpin.library_v2.dao;

import com.lexshpin.library_v2.model.Book;
import com.lexshpin.library_v2.model.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> index() {
        return null;
    }

    public Person show(int id) {
        return null;
    }

    public void add(Person person) {

    }

    public void update(int id, Person person) {

    }

    public void delete(int id) {

    }
    
    public List<Book> getBooksByPerson(int id) {
        return null;
    }
}
