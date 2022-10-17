package com.db.referencedata;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ReferenceDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReferenceDataApplication.class, args);
    }
}
