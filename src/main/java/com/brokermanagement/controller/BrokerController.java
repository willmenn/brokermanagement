package com.brokermanagement.controller;


import com.brokermanagement.model.Broker;
import com.brokermanagement.service.BrokerService;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/broker")
public class BrokerController {

    private BrokerService service;

    @Autowired
    public BrokerController(BrokerService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public Broker getBroker(@PathVariable("id") String id) {
        return service.getBroker(id);
    }

    @PostMapping
    public Resource createBroker(@RequestBody Broker broker) {
        Broker brokerResponse = service.createBroker(broker);
        Resource resource = new Resource();
        resource.add(linkTo(methodOn(BrokerController.class)
                .getBroker(brokerResponse.getBrokerId()))
                .withSelfRel());
        return resource;
    }

    @NoArgsConstructor
    @Builder
    static class Resource extends ResourceSupport {

    }
}
