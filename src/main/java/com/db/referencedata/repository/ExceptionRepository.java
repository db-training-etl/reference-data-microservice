package com.db.referencedata.repository;


import com.db.referencedata.entity.ExceptionLog;

public interface ExceptionRepository {
    void sendException(ExceptionLog exceptionLog);

}
