package com.db.referencedata.repository;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;

public class WebclientUtility implements WebclientRepository{

    WebClient webClient;

    String url;

    //Constructor for microservice consumer
    public WebclientUtility() {
        this.url = "localhost:8334"; //random port
        webClient = WebClient.create(url);
    }
    //Constructor for testing use.
    public WebclientUtility(String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }


    @Override
    public HashMap sendException(String exceptionName, String cause, String message, String trace, Date date) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("path").build())
                .body(Mono.just(exceptionName), String.class)
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
}
