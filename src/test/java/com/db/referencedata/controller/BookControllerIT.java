package com.db.referencedata.controller;

import com.db.referencedata.ReferenceDataApplication;
import com.db.referencedata.entity.Book;
import com.db.referencedata.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static com.db.referencedata.utils.ReferenceDataUtils.getExampleBook;
import static com.db.referencedata.utils.ReferenceDataUtils.getExampleBooks;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ReferenceDataApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerIT {

    @MockBean
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    List<Book> books;

    @Test
    public void findOneBookByIdTest() throws Exception{
        Book book = getExampleBook(1,"AAAAAA", "Source1", "Santander");

        given(bookService.findById(1)).willReturn(Optional.ofNullable(book));

        ResultActions response = mockMvc.perform(get("/books/1"));

        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.bookName", is("AAAAAA")));

    }

    @Test
    public void findAllBooksTest() throws Exception{
        books = getExampleBooks();
        given(bookService.findAll()).willReturn(books);

        ResultActions response = mockMvc.perform(get("/books"));

        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$[0].bookName", is("AAAAAA")));

    }

    @Test
    public void saveOneBookTest() throws Exception{
        Book book = getExampleBook(1,"AAAAAA", "Source1", "Santander");

        given(bookService.save(book)).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(patch("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));

        response.andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void saveMultipleBooksTest() throws Exception{
        books = getExampleBooks();

        given(bookService.saveAll(books)).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(patch("/books/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(books)));

        response.andExpect(status().isOk()).andDo(print());

    }

}
