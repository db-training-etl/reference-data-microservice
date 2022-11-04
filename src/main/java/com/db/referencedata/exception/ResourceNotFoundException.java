package com.db.referencedata.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomException {

  public ResourceNotFoundException(String errorMessage) {
    super(errorMessage);
  }


  public HttpStatus getHttpStatus(){
    return HttpStatus.NOT_FOUND;
  }
}