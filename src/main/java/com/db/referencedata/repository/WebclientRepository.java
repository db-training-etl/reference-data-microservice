package com.db.referencedata.repository;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;

@Repository
public interface WebclientRepository {

    HashMap sendException(HashMap exceptionLog);

}
