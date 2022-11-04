package com.db.referencedata.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Repository
public class ExceptionRepositoryWebClient implements ExceptionRepository {

    WebClient webClient;

    String url;

    //Constructor for microservice consumer
    public ExceptionRepositoryWebClient() {
        this.url = "localhost:8334"; //random port
        webClient = WebClient.create(url);
    }
    //Constructor for testing use.
    public ExceptionRepositoryWebClient(String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }


    @Override
    public HashMap sendException(HashMap exceptionLog) {

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/exceptions").build())
                .body(BodyInserters.fromValue(exceptionLog))
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
}
