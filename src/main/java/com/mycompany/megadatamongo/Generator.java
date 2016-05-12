/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.megadatamongo;

import com.mongodb.client.MongoCollection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author mahbub
 */
public class Generator {
    public long generate(MongoCollection<Document> collection, int amount) throws IOException {
        long start = System.currentTimeMillis();
        long value;
        SecureRandom random=null;
        int modV = (int) Math.log10(amount)*10;
        FileWriter fw = new FileWriter("store.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter fout = new PrintWriter(bw);
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("No secure random algorithm found, "+ex.getMessage());
            return -1;
        }
        List<Document> nums = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            value = random.nextLong();
            nums.add(new Document(value+"", new Person()));
            if (i%modV==0) {
                System.out.println("Adding "+nums.size()+" items");
                fout.println(value);
                collection.insertMany(nums);
                nums.clear();
            }
        }
        if (!nums.isEmpty()) {
            collection.insertMany(nums);
        }
        fout.flush();
        fout.close();
        return System.currentTimeMillis()-start;
    }
}
