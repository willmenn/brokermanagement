package com.brokermanagement.controller;

import com.brokermanagement.model.Manager;
import com.brokermanagement.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ManagerController {

    private ManagerService service;

    @Autowired
    private ManagerController(ManagerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/manager", method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Manager save(@RequestBody Manager manager){
        return service.save(manager);
    }

    @RequestMapping(value = "manager", method = GET)
    @ResponseStatus(OK)
    public Manager get(@RequestParam String manager, @RequestParam String pass){
        return service.get(manager,pass);
    }
}
