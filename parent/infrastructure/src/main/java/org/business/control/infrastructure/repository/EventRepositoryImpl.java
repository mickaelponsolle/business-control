package org.business.control.infrastructure.repository;

import java.time.format.DateTimeFormatter;

import org.bson.Document;
import org.business.control.business.command.configuration.task.AddTaskConfigurationCommand;
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

	Document commandDocument = null;
	if (event.getCommand() != null && event.getCommand() instanceof AddTaskConfigurationCommand) {
	    commandDocument = new Document().append("title",
		    ((AddTaskConfigurationCommand) event.getCommand()).getTitle());
	}

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	Document eventDocument = new Document().append("dateTime", event.getDateTime().format(formatter))
		.append("command", commandDocument);

	collection.insertOne(eventDocument);
    }
}
