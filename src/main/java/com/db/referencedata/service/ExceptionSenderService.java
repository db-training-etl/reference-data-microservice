package com.db.referencedata.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public class ExceptionSenderService {

    WebClient webClient;

    String url;

    //Constructor for microservice consumer
    public ExceptionSenderService() {
        this.url = "localhost:8334"; //random port
        webClient = WebClient.create(url);
    }
    //Constructor for testing use.
    public ExceptionSenderService(String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }

    public HashMap sendException(String exceptionName) {

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("path").build())
                .body(Mono.just(exceptionName), String.class)
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
        }
}