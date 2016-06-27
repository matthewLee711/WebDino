package com.interns.webdino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.interns.webdino.data.MongoDataHelper;
import com.mongodb.MongoClient;

@Configuration
public class Configurator {

    @Bean(name="mongoClient")
    public MongoClient getMongoClient(){
        return new MongoClient("localhost" , 27017);
    }

    @Bean(name="mongoDataHelper")
    public MongoDataHelper getMongoDataHelper(){
        return new MongoDataHelper();
    }

}
