package com.db.referencedata.controller;

import com.db.referencedata.entity.Book;
import com.db.referencedata.entity.ChunkBooks;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@Validated
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
    public ResponseEntity<Book> save(@Valid @RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("bulk")
    public ResponseEntity<List<Book>> saveAll(@RequestBody List<@Valid Book> books) throws ListEmptyException {
        return new ResponseEntity<>(bookService.saveAll(books), HttpStatus.OK);
    }

    @PutMapping("chunk")
    public ResponseEntity<List<Book>> saveChunk(@Valid @RequestBody ChunkBooks chunkBooks) throws ListEmptyException {
        return new ResponseEntity<>(bookService.saveAll(chunkBooks.getChunkList()), HttpStatus.OK);
    }
}
