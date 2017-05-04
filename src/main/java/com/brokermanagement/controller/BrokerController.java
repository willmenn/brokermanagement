package com.brokermanagement.controller;


import com.brokermanagement.model.Broker;
import com.brokermanagement.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/broker")
public class BrokerController {

    private BrokerService service;

    @Autowired
    public BrokerController(BrokerService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public Broker getBroker(@PathVariable("id") Integer id) {
        return service.getBroker(id);
    }

    @PostMapping
    public Broker createBroker(@RequestBody Broker broker) {
        return service.createBroker(broker);
    }
}
