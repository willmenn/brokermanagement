package com.brokermanagement.controller;

import com.brokermanagement.controller.BrokerController.Resource;
import com.brokermanagement.model.ShiftPlace;
import com.brokermanagement.service.ShiftPlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
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

    @RequestMapping(value = "shiftPlace/manager/{name}", method = GET)
    @ResponseStatus(OK)
    public List<ShiftPlace> getShiftPlaceByManagerName(@PathVariable("name") String name) {
        return service.getShiftPlaceByManagersName(name);
    }

    @RequestMapping(value = "shiftPlace/manager/{name}/count", method = GET)
    @ResponseStatus(OK)
    public ShiftPlaceCount getCountOfShiftPlaceByManagerName(@PathVariable("name") String name) {
        return new ShiftPlaceCount(service.getCountOfShiftPlaceByManagersName(name));
    }

    @RequestMapping(value = "shiftPlace", method = POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Resource createShiftPlace(@RequestBody ShiftPlaceDTO shiftPlace) {
        ShiftPlace shiftPlaceCreated = service.createShiftPlace(shiftPlace.convertToShiftPlace());
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

    @Data
    @AllArgsConstructor
    public class ShiftPlaceCount {
        private Integer count;
    }

    @Data
    @AllArgsConstructor
    public class ShiftPlaceDTO {
        private String shiftPlaceId;
        private String name;
        private String address;
        private String managersName;
        private Integer SUN;
        private Integer MON;
        private Integer TUE;
        private Integer WED;
        private Integer THU;
        private Integer FRI;
        private Integer SAT;

        public ShiftPlace convertToShiftPlace(){
            Map<String,Integer> days = newHashMap();
            days.put("SUN", SUN);
            days.put("MON", MON);
            days.put("TUE", TUE);
            days.put("WED", WED);
            days.put("THU", THU);
            days.put("FRI", FRI);
            days.put("SAT", SAT);

            return ShiftPlace.builder()
                    .shiftPlaceId(shiftPlaceId)
                    .managersName(managersName)
                    .address(address)
                    .name(name)
                    .days(days)
                    .build();
        }

    }
}
