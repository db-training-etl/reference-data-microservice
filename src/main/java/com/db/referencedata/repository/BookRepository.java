package com.db.referencedata.repository;

import com.db.referencedata.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Integer> {
}
