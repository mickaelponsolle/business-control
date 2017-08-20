package org.business.control.business.aggregate;

import java.util.UUID;

import org.business.control.business.event.Event;
import org.business.control.business.exception.aggregate.TaskConfigurationException;
import org.business.control.business.utils.Money;
import org.business.control.business.utils.Pair;

public class TaskConfiguration extends Aggregate {
    private UUID id;

    private String title;
    private Money price;

    public Pair<TaskConfiguration, Event> build(String title, Money price) {
        TaskConfiguration taskConfiguration = new TaskConfiguration();
        taskConfiguration.id = UUID.randomUUID();
        taskConfiguration.title = title;
        taskConfiguration.price = price;

        checkCommandValidity(title, price);

        Event createTaskConfigurationEvent = new Event();
        createTaskConfigurationEvent.aggregateType = taskConfiguration.getClass();

        return new Pair<TaskConfiguration, Event>(taskConfiguration, createTaskConfigurationEvent);
    }

    private void checkCommandValidity(String title, Money price) {
        TaskConfigurationException exception = null;
        if (title == null) {
            if (exception == null) {
                exception = new TaskConfigurationException();
            }
            exception.setNullTitle(true);
        }
        if (price == null) {
            if (exception == null) {
                exception = new TaskConfigurationException();
            }
            exception.setNullPrice(true);
        } else if (price.getValue().signum() < 0) {
            if (exception == null) {
                exception = new TaskConfigurationException();
            }
            exception.setNegativePrice(true);
        }
        if (exception != null) {
            throw exception;
        }
    }
}
