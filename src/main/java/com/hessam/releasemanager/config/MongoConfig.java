package com.hessam.releasemanager.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
public class MongoConfig {

    /*private static final String URL = "localhost";
    private static final String NAME = "embedded_mongo";

    @Bean
    public MongoTemplate mongoTemplate() throws IOException{

        EmbeddedMongoFactoryBean mongoFactoryBean = new EmbeddedMongoFactoryBean();
        mongoFactoryBean.setBindIp(URL);
        MongoClient client = mongoFactoryBean.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(client,NAME);
    }*/
}
