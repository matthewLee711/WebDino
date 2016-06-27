package com.interns.webdino.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoDataHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDataHelper.class);

    @Autowired
    private MongoClient mongoClient;

    public void addDocument(int id, String content){

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
