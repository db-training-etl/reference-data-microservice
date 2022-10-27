package com.db.referencedata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ListEmptyException extends Exception{
    public ListEmptyException(String errorMessage){
        super(errorMessage);
    }
}
