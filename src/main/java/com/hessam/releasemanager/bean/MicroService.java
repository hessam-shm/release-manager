package com.hessam.releasemanager.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MicroService {

    private String name;
    private int version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MicroService that = (MicroService) o;
        return getVersion() == that.getVersion() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVersion());
    }

    //TODO:in compliance with json may change
    @Override
    public String toString() {
        return "MicroService{" +
                "name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
