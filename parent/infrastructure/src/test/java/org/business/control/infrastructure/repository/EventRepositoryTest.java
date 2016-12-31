package org.business.control.infrastructure.repository;

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
    }
}
