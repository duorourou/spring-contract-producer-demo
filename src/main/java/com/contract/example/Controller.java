package com.contract.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @RequestMapping(path = "/")
    public String list() {
        return this.service.go();
    }
}
