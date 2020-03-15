package com.hessam.releasemanager.service;

import com.hessam.releasemanager.bean.Deployment;
import com.hessam.releasemanager.bean.MicroService;
import com.hessam.releasemanager.repository.DeploymentDao;
import com.hessam.releasemanager.repository.SystemVersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("deploymentService")
public class DeploymentServiceImpl implements DeploymentService {

    @Autowired
    DeploymentDao deploymentDao;
    @Autowired
    SystemVersionDao systemVersionDao;

    @Override
    public ArrayList<MicroService> getMicroServices(int systemVersion) {
        Deployment deployment = deploymentDao.findBySystemVersion(systemVersion);
        if(deployment == null)
            return null;
        return new ArrayList<>(deployment.getServices());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public int newDeployment(MicroService microService) {
        int currentSystemVersion = systemVersionDao.getCurrentSystemVersion();
        Deployment deployment = deploymentDao.findBySystemVersion(currentSystemVersion);
        if(deployment == null){
            deployment = new Deployment(0,new ArrayList<MicroService>());
        }
        List<MicroService> services = deployment.getServices();

        if(services.contains(microService)){
            return currentSystemVersion;
        }

        Iterator<MicroService> iterator = services.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getName().equalsIgnoreCase(microService.getName())){
                iterator.remove();
                break;
            }
        }

        services.add(microService);

        Deployment newDeployment = new Deployment(systemVersionDao.getNextSystemVersion(),services);
        deploymentDao.create(newDeployment);
        return newDeployment.getSystemVersion();
    }

}
