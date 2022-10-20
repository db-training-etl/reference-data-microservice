package com.db.referencedata.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Book {
    @Id
    Integer bookId;
    String bookName;
    String bookAddress;
    String entity;
}
