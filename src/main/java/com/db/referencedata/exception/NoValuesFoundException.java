package com.db.referencedata.exception;

public class NoValuesFoundException extends Exception{
    public NoValuesFoundException(String errorMessage){
        super(errorMessage);
    }
}
