package com.db.referencedata.webclient;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;

@Repository
public interface WebclientRepository {

    HashMap sendException(String exceptionName, String type, String message, String trace, Date date);

}
