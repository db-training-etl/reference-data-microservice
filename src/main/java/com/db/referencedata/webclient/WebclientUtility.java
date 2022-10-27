package com.db.referencedata.webclient;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

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
    public HashMap sendException(HashMap exceptionLog) {

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/exceptions").build())
                .body(BodyInserters.fromValue(exceptionLog))
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
}
