package org.business.control.infrastructure.repository;

import java.util.Arrays;

import org.bson.Document;
import org.business.control.business.event.BusinessEvent;
import org.business.control.business.repository.EventRepository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class EventRepositoryImpl implements EventRepository {
    MongoCollection<Document> collection;

    public EventRepositoryImpl(MongoClient mongoClient, String databaseName, String collectionName) {
	collection = mongoClient.getDatabase(databaseName).getCollection(collectionName);
    }

    public void store(BusinessEvent event) {
	// Document document = new Document().append("event", event);
	Document document = new Document("name", "MongoDB").append("type", "database").append("count", 1)
		.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
		.append("info", new Document("x", 203).append("y", 102));
	collection.insertOne(document);

    }
}
