package com.hessam.releasemanager.repository;

import com.hessam.releasemanager.bean.SystemVersion;

public interface SystemVersionDao {

    public int getCurrentSystemVersion();
    public int getNextSystemVersion();
    public void create(SystemVersion version);
}
