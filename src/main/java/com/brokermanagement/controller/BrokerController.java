package com.brokermanagement.controller;


import com.brokermanagement.model.Broker;
import com.brokermanagement.service.BrokerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/broker")
public class BrokerController {

    private BrokerService service;

    @GetMapping(path = "/{id}")
    public Broker getBroker(Integer id) {
        return service.getBroker(id);
    }
}
