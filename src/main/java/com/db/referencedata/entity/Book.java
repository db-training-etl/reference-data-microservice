package com.db.referencedata.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Book {
    @Id
    Integer bookId;
    @NotNull
    String bookName;
    @NotNull
    String bookAddress;
    @NotNull
    String entity;
}
