package com.db.referencedata.controller;

import com.db.referencedata.entity.Book;
import com.db.referencedata.entity.ChunkBooks;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.db.referencedata.utils.ReferenceDataUtils.getExampleBook;
import static com.db.referencedata.utils.ReferenceDataUtils.getExampleBooks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class BookControllerTest {

    BookService bookService;
    BookController bookController;
    List<Book> books;

    ChunkBooks chunk;

    @BeforeEach
    void setup() {
        bookService = mock(BookService.class);
        bookController = new BookController(bookService);

    }

    @Test
    public void findOneBookByIdTest(){
        Book book = getExampleBook(1,"Pepe", "Something", "Sevilla");

        given(bookService.findById(1)).willReturn(book);

        assertEquals(book, bookController.findById(1).getBody());
    }

    @Test
    public void findAllBooksTest() throws ListEmptyException {
        books = getExampleBooks();

        given(bookService.findAll()).willReturn(books);

        assertEquals(books, bookController.findAll().getBody());
    }

    @Test
    public void saveOneBookTest(){
        Book book = getExampleBook(1,"Pepe", "Something", "Sevilla");
        ResponseEntity<Book> response = new ResponseEntity<Book>(book, HttpStatus.OK);

        given(bookService.save(book)).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, bookController.save(book));
    }

    @Test
    public void saveMultipleBooksTest(){
        books = getExampleBooks();
        ResponseEntity<List<Book>> response = new ResponseEntity<>(books, HttpStatus.OK);

        given(bookService.saveAll(books)).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, bookController.saveAll(books));
    }

    @Test
    public void saveChunkTest(){
        chunk = getExampleChunkBooks();
        ResponseEntity<List<Book>> response = new ResponseEntity<>(chunk.getChunkList(), HttpStatus.OK);

        given(bookService.saveChunk(any(List.class))).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, bookController.saveChunk(chunk));
    }

    public ChunkBooks getExampleChunkBooks(){
        ChunkBooks chunk = new ChunkBooks();

        chunk.setProcessId(1);
        chunk.setNumChunks(1);
        chunk.setSize(10);
        chunk.setChunkList(getExampleBooks());

        return chunk;
    }
}
