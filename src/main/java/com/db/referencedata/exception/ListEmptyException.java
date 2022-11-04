package com.db.referencedata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ListEmptyException extends CustomException {

    public ListEmptyException(String errorMessage) {
        super(errorMessage);
    }


    public HttpStatus getHttpStatus(){
        return HttpStatus.NO_CONTENT;
    }
}
