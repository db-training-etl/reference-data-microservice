package com.db.referencedata.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExceptionService {

    WebClient webClient;

    String baseUrl;

    //WebTestClient
    public ExceptionService(String baseUrl) {
        this.baseUrl = baseUrl;
        webClient = WebClient.create(baseUrl);
    }

    public ExceptionService() {
        this.baseUrl = "localhost:8081"; //para consumir la api de Exception
        webClient = WebClient.create(baseUrl);
    }

}
