package com.db.referencedata.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException{

    public CustomException(String errorMessage){
        super(errorMessage);
    }


    public abstract HttpStatus getHttpStatus();
}
