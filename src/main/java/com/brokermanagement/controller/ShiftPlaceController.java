package com.brokermanagement.controller;

import com.brokermanagement.controller.BrokerController.Resource;
import com.brokermanagement.model.DayEnum;
import com.brokermanagement.model.Shift;
import com.brokermanagement.model.ShiftPlace;
import com.brokermanagement.service.ShiftPlaceService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    public Resource updateShiftPlace(@PathVariable("id") String id, @RequestBody ShiftPlaceDTO shiftPlace) {
        ShiftPlace shiftPlaceCreated = service.updateShiftPlace(id, shiftPlace.convertToShiftPlace());
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
    @NoArgsConstructor
    public static class ShiftPlaceDTO {
        private String shiftPlaceId;
        private String name;
        private String address;
        private String managersName;
        private Shift SUN;
        private Shift MON;
        private Shift TUE;
        private Shift WED;
        private Shift THU;
        private Shift FRI;
        private Shift SAT;

        @JsonIgnore
        public ShiftPlace convertToShiftPlace() {
            Map<DayEnum, Shift> days = newHashMap();
            addToMap(DayEnum.SUN, SUN, days);
            addToMap(DayEnum.MON, MON, days);
            addToMap(DayEnum.TUE, TUE, days);
            addToMap(DayEnum.WED, WED, days);
            addToMap(DayEnum.THU, THU, days);
            addToMap(DayEnum.FRI, FRI, days);
            addToMap(DayEnum.SAT, SAT, days);

            return ShiftPlace.builder()
                    .shiftPlaceId(shiftPlaceId)
                    .managersName(managersName)
                    .address(address)
                    .name(name)
                    .daysV3(days)
                    .build();
        }

        private void addToMap(DayEnum day, Shift shift, Map<DayEnum, Shift> days) {
            if (shift != null && shift.isNotNull()) {
                days.put(day, shift);
            }
        }

    }

}
