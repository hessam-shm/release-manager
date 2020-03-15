package com.hessam.releasemanager.repository;

import com.hessam.releasemanager.bean.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("deploymentDao")
public class DeploymentDaoImpl implements DeploymentDao {

    final static String COLLECTION = "deployments";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void create(Deployment deployment) {
        mongoTemplate.save(deployment);
    }

    @Override
    public Deployment findBySystemVersion(int systemVersion) {

        Query query = new Query(Criteria.where("systemVersion").is(systemVersion));
        return mongoTemplate.findOne(query, Deployment.class, COLLECTION);
    }

}
