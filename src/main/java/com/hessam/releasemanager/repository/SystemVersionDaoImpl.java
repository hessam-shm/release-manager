package com.hessam.releasemanager.repository;

import com.hessam.releasemanager.bean.SystemVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("systemVersionDao")
public class SystemVersionDaoImpl implements SystemVersionDao {

    private static final String KEY = "systemVersion";

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public int getCurrentSystemVersion(){
        Query query = new Query(Criteria.where("_id").is(KEY));
        return mongoTemplate.findOne(query,SystemVersion.class).getVersion();
    }

    @Override
    public int getNextSystemVersion() {

        Query query = new Query(Criteria.where("_id").is(KEY));
        Update update = new Update();
        update.inc("version",1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        SystemVersion version = mongoTemplate.findAndModify(query,update,options,SystemVersion.class);

        return version.getVersion();
    }

    @Override
    public void create(SystemVersion version){
        mongoTemplate.save(version);
    }
}
