package com.db.referencedata.service;


import com.db.referencedata.entity.Book;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.exception.ResourceNotFoundException;
import com.db.referencedata.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));
    }

    public List<Book> findAll() throws ListEmptyException {
        List<Book> books = bookRepository.findAll();

        if(books.isEmpty())
            throw new ListEmptyException("List of books is empty");
        else{
            return books;
        }
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> saveAll(List<Book> books) {
        if(books.isEmpty())
            throw new ListEmptyException("List of books is empty");
        return bookRepository.saveAll(books);

    }

    public List<Book> saveChunk(List<Book> books) throws ListEmptyException {
        if(books.isEmpty())
            throw new ListEmptyException("List of books is empty");
        return bookRepository.saveAll(books);
    }
}
