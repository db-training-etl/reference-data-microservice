package com.db.referencedata.controller;

import com.db.referencedata.entity.Book;
import com.db.referencedata.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    public Optional<Book> findById(@PathVariable int id){
        return bookService.findById(id);
    }

    @GetMapping("")
    public Iterable<Book> findAll(){
        return bookService.findAll();
    }

    @PatchMapping("")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PatchMapping("bulk")
    public ResponseEntity<Iterable<Book>> saveAll(@RequestBody List<Book> books) {
        bookService.saveAll(books);
        return new ResponseEntity<Iterable<Book>>(books, HttpStatus.OK);
    }
}
