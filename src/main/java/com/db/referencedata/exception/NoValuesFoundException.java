package com.db.referencedata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoValuesFoundException extends Exception{
    public NoValuesFoundException(String errorMessage){
        super(errorMessage);
    }
}
