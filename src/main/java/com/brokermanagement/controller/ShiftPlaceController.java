package com.brokermanagement.controller;

import com.brokermanagement.controller.BrokerController.Resource;
import com.brokermanagement.model.ShiftPlace;
import com.brokermanagement.service.ShiftPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class ShiftPlaceController {

    private ShiftPlaceService service;

    @Autowired
    public ShiftPlaceController(ShiftPlaceService service) {
        this.service = service;
    }

    @RequestMapping(value = "shiftPlace/{id}", method = GET)
    @ResponseStatus(OK)
    public ShiftPlace getShiftPlace(@PathVariable("id") String id) {
        return service.getShiftPlace(id);
    }

    @RequestMapping(value = "shiftPlace", method = POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Resource createShiftPlace(@RequestBody ShiftPlace shiftPlace) {
        ShiftPlace shiftPlaceCreated = service.createShiftPlace(shiftPlace);
        return createResource(shiftPlaceCreated.getShiftPlaceId());
    }

    @RequestMapping(value = "shiftPlace/{id}", method = PUT,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Resource updateShiftPlace(@PathVariable("id") String id, @RequestBody ShiftPlace shiftPlace) {
        ShiftPlace shiftPlaceCreated = service.updateShiftPlace(id, shiftPlace);
        return createResource(shiftPlaceCreated.getShiftPlaceId());
    }

    @RequestMapping(value = "shiftPlace/{id}", method = DELETE)
    @ResponseStatus(OK)
    public void deleteShiftPlace(@PathVariable("id") String id) {
        service.deleteShiftPlace(id);
    }

    private Resource createResource(String id) {
        Resource resource = new Resource();
        resource.add(linkTo(methodOn(ShiftPlaceController.class)
                .getShiftPlace(id))
                .withSelfRel());
        return resource;
    }
}
