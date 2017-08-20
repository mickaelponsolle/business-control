package org.business.control.business.aggregate;

import java.util.UUID;

import org.business.control.business.exception.aggregate.TaskConfigurationException;
import org.business.control.business.utils.Money;

public class TaskConfiguration {
    private UUID id;

    private String title;
    private Money price;

    public TaskConfiguration() {
        // TODO Auto-generated constructor stub
    }

    public static TaskConfiguration build(String title, Money price) {
        TaskConfiguration taskConfiguration = new TaskConfiguration();
        taskConfiguration.id = UUID.randomUUID();
        taskConfiguration.title = title;
        taskConfiguration.price = price;

        checkCommandValidity(title, price);
        return taskConfiguration;
    }

    private static void checkCommandValidity(String title, Money price) {
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
