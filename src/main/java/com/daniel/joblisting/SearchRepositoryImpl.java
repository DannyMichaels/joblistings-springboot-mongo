package com.daniel.joblisting;

import com.daniel.joblisting.model.Post;
import com.mongodb.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;


@Repository
public class SearchRepositoryImpl implements SearchRepository {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {
        List<Post> posts = new ArrayList<Post>();

        MongoDatabase database = client.getDatabase("jobsDb");
        MongoCollection<Document> collection = database.getCollection("post");

        AggregateIterable<Document> results = collection.aggregate(Arrays.asList(
                new Document("$match",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("technologies", "description", "profile")))),
                new Document("$sort",
                        new Document("experience", 1L)),
                new Document("$limit", 5L)
        ));


        results.forEach(doc -> posts.add(converter.read(Post.class, doc)));


        return posts;
    }
}