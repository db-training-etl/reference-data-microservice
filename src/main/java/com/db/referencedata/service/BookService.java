package com.db.referencedata.service;


import com.db.referencedata.entity.Book;
import com.db.referencedata.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> saveAll(List<Book> books) {
        return bookRepository.saveAll(books);
    }
}
