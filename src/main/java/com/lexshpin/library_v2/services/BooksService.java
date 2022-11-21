package com.lexshpin.library_v2.services;

import com.lexshpin.library_v2.model.Book;
import com.lexshpin.library_v2.repositories.BooksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {return booksRepository.findAll();}

    public List<Book> findAll(Sort sort) {return booksRepository.findAll(sort);}

    public Page<Book> findAll(Pageable pageRequest) {return booksRepository.findAll(pageRequest);}

    public Book findOne(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
