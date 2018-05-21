package com.brokermanagement.controller;

import com.brokermanagement.model.Manager;
import com.brokermanagement.service.ManagerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ManagerController {

    private ManagerService service;

    @Autowired
    private ManagerController(ManagerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/manager", method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Manager save(@RequestBody Manager manager) {
        return service.save(manager);
    }

    @RequestMapping(value = "/manager", method = GET)
    @ResponseStatus(OK)
    public Manager get(@RequestParam String manager, @RequestParam String pass) {
        return service.get(manager, pass);
    }


    @RequestMapping(value = "/manager/messages", method = GET)
    @ResponseStatus(OK)
    public List<Manager.Message> getMessagesByManager(@RequestParam String manager) {
        return service.getMessagesByManger(manager);
    }

    @RequestMapping(value = "/manager/messages", method = POST)
    @ResponseStatus(OK)
    public List<Manager.Message> createMessagesByManager(@RequestParam String manager, @RequestParam String message) {
        return service.createMessage(manager, message);
    }

    @RequestMapping(value = "/manager/schedule", method = GET)
    @ResponseStatus(OK)
    public ManagerScheduleId getScheduleId(@RequestParam String manager) {
        return ManagerScheduleId.builder().scheduleId(service.get(manager).getScheduleId()).build();
    }

    @RequestMapping(value = "/manager", method = PUT, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Manager updateManagerSchedule(@RequestParam String manager, @RequestParam String scheduleId) {
        return service.updateManagerSchedule(manager, scheduleId);
    }

    @AllArgsConstructor
    @Data
    @Builder
    private static class ManagerScheduleId {
        private String scheduleId;
    }
}
