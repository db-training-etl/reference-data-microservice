package com.db.referencedata.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String name;
    String type;
    String message;
    String trace;
    Date cobDate;

}