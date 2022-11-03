package com.db.referencedata.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface ExceptionRepository {

    HashMap sendException(HashMap exceptionLog);

}
