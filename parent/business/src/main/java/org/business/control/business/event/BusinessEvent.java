package org.business.control.business.event;

import java.time.LocalDateTime;

import org.business.control.business.command.BusinessCommand;

public class BusinessEvent {
    private final LocalDateTime dateTime;
    private final BusinessCommand command;

    public BusinessEvent(BusinessCommand command) {
	this.dateTime = LocalDateTime.now();
	this.command = command;
    }

    public LocalDateTime getDateTime() {
	return dateTime;
    }

    public BusinessCommand getCommand() {
	return command;
    }
}
