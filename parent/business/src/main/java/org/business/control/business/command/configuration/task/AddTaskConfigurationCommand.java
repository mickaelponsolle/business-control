package org.business.control.business.command.configuration.task;

import org.business.control.business.command.BusinessCommand;

public class AddTaskConfigurationCommand implements BusinessCommand {
    private final String title;

    public AddTaskConfigurationCommand(String title) {
	this.title = title;
    }

    public String getTitle() {
	return title;
    }
}
