package com.db.referencedata.controller;

import com.db.referencedata.entity.Book;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@PathVariable int id){
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> findAll() throws ListEmptyException {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping("bulk")
    public ResponseEntity<List<Book>> saveAll(@RequestBody List<Book> books) {
        return new ResponseEntity<List<Book>>(bookService.saveAll(books), HttpStatus.OK);
    }
}
