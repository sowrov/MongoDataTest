/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.megadatamongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import org.bson.Document;

/**
 *
 * @author mahbub
 */
public class Main {
    
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase db = mongoClient.getDatabase("mydb");
    Generator gn = new Generator();
    public static void main(String[] args) throws IOException {
        Main app = new Main();
        MongoCollection<Document> collection = app.db.getCollection("bigCollection");
//        collection.insertOne(new Document("abcd", "{'name':'bla', 'con':'la'}"));
        app.gn.generate(collection, 500000000);
        System.out.println("Test: "+collection.count());
        
//        BasicDBObject query = new BasicDBObject("abc", new BasicDBObject("$exists", true));
//        
////        query.get("abcd");
//        FindIterable<Document> docs = collection.find(query);
//        for (Document doc : docs) {
//            System.out.println("Doc: "+doc.toJson());
//        }
    }
}
