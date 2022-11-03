package com.db.referencedata.service;

import com.db.referencedata.entity.Book;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.db.referencedata.utils.ReferenceDataUtils.getExampleBook;
import static com.db.referencedata.utils.ReferenceDataUtils.getExampleBooks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;

    List<Book> books;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void findOneBookByIdTest(){
        Book book = getExampleBook(1,"Pepe", "Something", "Sevilla");

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        assertEquals(bookService.findById(1),book);
    }

    @Test
    public void findAllBooksTest() throws ListEmptyException {
        books = getExampleBooks();

        when(bookRepository.findAll()).thenReturn(books);

        assertNotNull(bookService.findAll());
        assertEquals(bookService.findAll(),books);
    }

    @Test
    public void findAllBooks_EmptyList_Test() throws ListEmptyException {
        books = new LinkedList<>();

        when(bookRepository.findAll()).thenReturn(books);

        assertThrows(ListEmptyException.class, () -> bookService.findAll());
    }

    @Test
    public void saveOneBookTest(){
        Book book = getExampleBook(1,"Pepe", "Something", "Sevilla");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        assertNotNull(bookService.save(new Book()));
        assertEquals(bookService.save(book),book);
    }

    @Test
    public void saveMultipleBooksTest(){
        books = getExampleBooks();

        when(bookRepository.saveAll(any())).thenReturn(books);

        assertNotNull(bookService.saveAll(new LinkedList<>()));
        assertEquals(bookService.saveAll(books),books);
    }

}
