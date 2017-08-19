package org.business.control.business2.aggregate;

import java.util.UUID;

import org.business.control.business2.exception.aggregate.TaskConfigurationException;
import org.business.control.business2.utils.Money;

public class TaskConfiguration {
    private UUID id;

    private String title;
    private Money price;

    public TaskConfiguration(String title, Money price) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.price = price;

        checkCommandValidity(title, price);

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
