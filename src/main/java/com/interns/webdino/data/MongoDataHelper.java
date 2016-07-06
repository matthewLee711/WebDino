package com.interns.webdino.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interns.webdino.perftest.Job;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoDataHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDataHelper.class);

    @Autowired
    private MongoClient mongoClient;

    /*public void addDocument(int id, String content){

        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("testCollection");

        if(collection == null){
            db.createCollection("testCollection");
        }

        collection.insertOne(new Document("id", id).append("content", content));

        List<Document> docs = collection.find().into(new ArrayList<Document>());

        if(docs != null){

            for(Document doc: docs){
                LOGGER.info(doc.toJson());
            }

        }

    }*/
    public void addDocument(int id, Job data){
    	System.out.println("DB id: " + id + "data: " + data.firstByte );
        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("testCollection");

        if(collection == null){
            db.createCollection("testCollection");
        }

        /*BasicDBObject doc = new BasicDBObject("title", "MongoDB").
           append("description", "database").
           append("likes", 100).
           append("url", "http://www.tutorialspoint.com/mongodb/").
           append("by", "tutorials point");
        collection.insertMany(doc);*/
        //data.name.toString()
        collection.insertOne(new Document("id", id).append("firstByte", data.firstByteAverage).append("loadTime", data.fullLoadAverage));
        //collection.save({username: data.name, password:"google123"});
        //collection.insertOne();
        List<Document> docs = collection.find().into(new ArrayList<Document>());

        if(docs != null){

            for(Document doc: docs){
                LOGGER.info(doc.toJson());
            }

        }

    }

    public List<Document> findAll(){

        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("testCollection");

        if(collection == null){
            db.createCollection("testCollection");
        }

        List<Document> docs = collection.find().into(new ArrayList<Document>());

        return docs;


    }

    public List<Document> find(int id){

        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("testCollection");

        if(collection == null){
            db.createCollection("testCollection");
        }

        List<Document> docs = collection.find(new Document("id", id)).into(new ArrayList<Document>());

        return docs;

    }

}
