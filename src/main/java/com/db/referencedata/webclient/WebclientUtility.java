package com.db.referencedata.webclient;

import org.springframework.web.reactive.function.BodyInserters;
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
    public HashMap sendException(String exceptionName, String type, String message, String trace, Date date) {

        HashMap<String,Object> requestBody = new HashMap<>();
        requestBody.put("name",exceptionName);
        requestBody.put("type",type);
        requestBody.put("message",message);
        requestBody.put("trace",trace);
        requestBody.put("cobDate",date);

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/exceptions").build())
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }
}
