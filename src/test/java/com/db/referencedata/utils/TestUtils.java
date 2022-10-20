package com.db.referencedata.utils;

import com.db.referencedata.entity.Book;
import com.db.referencedata.entity.Counterparty;

import java.util.LinkedList;
import java.util.List;

public class TestUtils {

    public static List<Counterparty> getExampleCounterparties(){
        List<Counterparty> counterparties = new LinkedList<>();
        counterparties.add(getExampleCounterparty(1,"AAAAAA", "Source1", "Santander"));
        counterparties.add(getExampleCounterparty(2,"BBB", "Source2", "BBVA"));
        counterparties.add(getExampleCounterparty(3,"CCC", "Source3", "CAIXABANK"));

        return counterparties;
    }

    public static Counterparty getExampleCounterparty(Integer id, String name, String source, String entity){
        Counterparty cpty = new Counterparty();
        cpty.setCounterpartyId(id);
        cpty.setCounterpartyName(name);
        cpty.setSource(source);
        cpty.setEntity(entity);

        return cpty;
    }

    public static List<Book> getExampleBooks(){
        List<Book> books = new LinkedList<>();
        books.add(getExampleBook(1,"AAAAAA", "Address1", "Santander"));
        books.add(getExampleBook(2,"BBB", "Address2", "BBVA"));
        books.add(getExampleBook(3,"CCC", "Address3", "CAIXABANK"));

        return books;
    }

    public static Book getExampleBook(Integer id, String name, String address, String entity){
        Book book = new Book();
        book.setBookId(id);
        book.setBookName(name);
        book.setBookAddress(address);
        book.setEntity(entity);

        return book;
    }

}
