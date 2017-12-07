package com.contract.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Service {

    @Value("${app.baseUrl:http://example.org}")
    private String base;

    private final RestTemplate restTemplate;

    public Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String go() {
        System.out.println("GO");
        return this.restTemplate.getForEntity(this.base + "/resource", String.class).getBody();
    }
}
