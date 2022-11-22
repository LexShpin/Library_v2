package com.lexshpin.library_v2.repositories;

import com.lexshpin.library_v2.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Page<Book> findAll(Pageable pageRequest);

    List<Book> findAll(Sort sort);

    List<Book> findByTitleStartingWith(String startingWith);
}
