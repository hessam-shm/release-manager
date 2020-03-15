package com.hessam.releasemanager.repository;

import com.hessam.releasemanager.bean.Deployment;

public interface DeploymentDao {

    public void create(Deployment deployment);

    public Deployment findBySystemVersion(int systemVersion);

}
