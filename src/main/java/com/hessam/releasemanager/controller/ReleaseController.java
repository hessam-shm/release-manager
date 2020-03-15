package com.hessam.releasemanager.controller;

import com.hessam.releasemanager.bean.MicroService;
import com.hessam.releasemanager.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        List<MicroService> response = deploymentService.getMicroServices(version);

        if(response == null)
            throw new NotFoundException();

        return response;
    }

    @PostMapping(value="/deploy", consumes = "application/json")
    public int deployService(@RequestBody MicroService microService){

        return deploymentService.newDeployment(microService);

    }
}
