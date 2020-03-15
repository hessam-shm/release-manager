package com.hessam.releasemanager.controller;

import com.hessam.releasemanager.bean.MicroService;
import com.hessam.releasemanager.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ReleaseController {

    @Autowired
    DeploymentService deploymentService;

    @GetMapping("/test")
    public String testUrl(){
        return "test succeeded";
    }

    @GetMapping(value = "/services", produces = "application/json")
    public List<MicroService> getServicesForVersion(@RequestParam String systemVersion){

        int version = 0;
        try{
            version = Integer.parseInt(systemVersion);
        } catch (NumberFormatException e){
            throw new BadRequestException();
        }

        List<MicroService> response = deploymentService.getMicroservices(version);

        if(response == null)
            throw new NotFoundException();

        return response;
    }

    @PostMapping(value="/deploy", consumes = "application/json")
    public int deployService(@RequestBody MicroService microService){

        return deploymentService.newDeployment(microService);

    }
}
