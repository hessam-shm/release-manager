package com.hessam.releasemanager.service;

import com.hessam.releasemanager.bean.Deployment;
import com.hessam.releasemanager.bean.MicroService;

import java.util.List;

public interface DeploymentService {

    public List<MicroService> getMicroServices(int systemVersion);

    public int newDeployment(MicroService microService);

}
