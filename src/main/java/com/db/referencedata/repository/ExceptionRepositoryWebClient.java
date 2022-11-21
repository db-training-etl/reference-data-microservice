package com.db.referencedata.repository;

import com.db.referencedata.entity.ExceptionLog;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class ExceptionRepositoryWebClient implements ExceptionRepository {

    WebClient webClient;

    String url;

    //Constructor for microservice consumer
    public ExceptionRepositoryWebClient() {
        this.url = "http://localhost:8081"; //random port
        webClient = WebClient.create(url);
    }

    //Constructor for testing use.
    public ExceptionRepositoryWebClient(String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }

    @Override
    public void sendException(ExceptionLog exceptionLog) {
        webClient.post()
                .uri("/exceptions")
                .body(BodyInserters.fromValue(exceptionLog))
                .retrieve()
                .toEntity(ExceptionLog.class)
                .block();
    }
}
