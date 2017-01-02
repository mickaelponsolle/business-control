package org.business.control.infrastructure.repository;

import org.bson.Document;
import org.business.control.business.command.configuration.task.AddTaskConfigurationCommand;
import org.business.control.business.event.BusinessEvent;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

public class EventRepositoryTest {
    private static MongoClient mongoClient;

    @BeforeClass
    public static void setUp() {
	Fongo fongo = new Fongo("mongo server");
	mongoClient = fongo.getMongo();
    }

    @Test
    public void whenEventRepositoryStoredIsCalledThenStoreAnEvent() {
	EventRepositoryImpl eventRepository = new EventRepositoryImpl(mongoClient, "writeModel", "event");
	BusinessEvent event = new BusinessEvent(new AddTaskConfigurationCommand("addTaskConfiguration"));
	eventRepository.store(event);

	Long eventCounter = mongoClient.getDatabase("writeModel").getCollection("event").count();
	Assert.assertEquals("The database should contain 1 event", Long.valueOf(1), eventCounter);

	Document document = mongoClient.getDatabase("writeModel").getCollection("event").find().first();
	Assert.assertTrue("The document should contain a dateTime field", document.get("dateTime") != null);
	Assert.assertTrue("The document should contain a command field", document.get("command") != null);
	Document commandDocument = (Document) document.get("command");
	Assert.assertTrue("The command document should contain a title field", commandDocument.get("title") != null);
	Assert.assertTrue("The command document should contain a title field",
		commandDocument.get("title").equals("addTaskConfiguration"));

    }
}
