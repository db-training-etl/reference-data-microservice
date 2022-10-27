package com.db.referencedata.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class ExceptionLog {

    public ExceptionLog(String name, String message, String trace, Date cobDate) {
        this.name = name;
        this.type = name;
        this.message = message;
        this.trace = trace;
        this.cobDate = cobDate;
    }

    String name;
    String type;
    String message;
    String trace;
    Date cobDate;

}