package com.hessam.releasemanager.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "deployments")
public class Deployment {

    @Id
    private String id;
    private int systemVersion;
    private List<MicroService> services;

    public Deployment(int systemVersion, List<MicroService> services) {
        this.systemVersion = systemVersion;
        this.services = services;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(int systemVersion) {
        this.systemVersion = systemVersion;
    }

    public List<MicroService> getServices() {
        return services;
    }

    public void setServices(List<MicroService> services) {
        this.services = services;
    }
}
