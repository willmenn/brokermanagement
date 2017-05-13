package com.brokermanagement.controller;


import com.brokermanagement.exception.BrokerManagementException;
import com.brokermanagement.model.Broker;
import com.brokermanagement.service.BrokerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/broker")
public class BrokerController {

    private BrokerService service;

    @Autowired
    public BrokerController(BrokerService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(OK)
    public Broker getBroker(@PathVariable("id") String id) {
        return service.getBroker(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Resource createBroker(@RequestBody Broker broker) {
        Broker brokerResponse = service.createBroker(broker);
        Resource resource = createResource(brokerResponse.getBrokerId());
        return resource;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(OK)
    public void deleteBroker(@PathVariable("id") String id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(OK)
    public Resource updateBroker(@PathVariable("id") String id, @RequestBody Broker broker) {
        Broker updateBroker = service.updateBroker(id, broker);
        return createResource(updateBroker.getBrokerId());
    }

    @ExceptionHandler(BrokerManagementException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessage brokerManagementExceptionHandler(BrokerManagementException exception) {
        return ErrorMessage.builder()
                .brokerId(exception.getBrokerId())
                .message(exception.getMessage())
                .build();
    }

    private Resource createResource(String brokerId) {
        Resource resource = new Resource();
        resource.add(linkTo(methodOn(BrokerController.class)
                .getBroker(brokerId))
                .withSelfRel());
        return resource;
    }

    @NoArgsConstructor
    static class Resource extends ResourceSupport {

    }

    @AllArgsConstructor
    @Builder
    static class ErrorMessage extends ResourceSupport {
        private String message;
        private String brokerId;
    }
}
