package com.lexshpin.library_v2.dao;

import com.lexshpin.library_v2.model.Book;
import com.lexshpin.library_v2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECt * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, yearofbirth) VALUES(?, ?)", person.getName(), person.getYearOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, yearofbirth=? WHERE id=?", person.getName(), person.getYearOfBirth(), person.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
    
    public List<Book> getBooksByPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE assigned_to=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
