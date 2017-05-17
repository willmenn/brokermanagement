package com.brokermanagement.controller;

import com.brokermanagement.exception.SellsManagementException;
import com.brokermanagement.model.Sells;
import com.brokermanagement.service.SellsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class SellsController {

    private SellsService service;

    @Autowired
    public SellsController(SellsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/sells/{id}", method = GET)
    @ResponseStatus(OK)
    public Sells getSells(@PathVariable("id") String id) {
        return service.getSellsId(id);
    }

    @RequestMapping(value = "/sell/manager/{name}", method = GET)
    @ResponseStatus(OK)
    public List<Sells> getSellssByManager(@PathVariable("name") String name) {
        return service.getSellsByManager(name);
    }

    @RequestMapping(value = "/sell", method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public SellsController.Resource createSells(@RequestBody Sells broker) {
        Sells brokerResponse = service.createSells(broker);
        SellsController.Resource resource = createResource(brokerResponse.getId());
        return resource;
    }

    @RequestMapping(value = "/sell/{id}", method = DELETE)
    @ResponseStatus(OK)
    public void deleteSells(@PathVariable("id") String id) {
        service.delete(id);
    }

    @RequestMapping(value = "/sell/{id}", method = PUT, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public SellsController.Resource updateSells(@PathVariable("id") String id, @RequestBody Sells broker) {
        Sells updateSells = service.updateSells(id, broker);
        return createResource(updateSells.getId());
    }


    @ExceptionHandler(SellsManagementException.class)
    @ResponseStatus(NOT_FOUND)
    public SellsController.ErrorMessage brokerManagementExceptionHandler(SellsManagementException exception) {
        return SellsController.ErrorMessage.builder()
                .brokerId(exception.getId())
                .message(exception.getMessage())
                .build();
    }

    private SellsController.Resource createResource(String sellsId) {
        SellsController.Resource resource = new SellsController.Resource();
        resource.add(linkTo(methodOn(SellsController.class)
                .getSells(sellsId))
                .withSelfRel());
        return resource;
    }

    @NoArgsConstructor
    public static class Resource extends ResourceSupport {

    }

    @AllArgsConstructor
    @Builder
    static class ErrorMessage extends ResourceSupport {
        private String message;
        private String brokerId;
    }
}
