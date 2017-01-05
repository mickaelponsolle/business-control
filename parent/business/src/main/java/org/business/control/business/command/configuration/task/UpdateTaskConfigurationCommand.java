package org.business.control.business.command.configuration.task;

import org.business.control.business.command.BusinessCommand;

public class UpdateTaskConfigurationCommand implements BusinessCommand {
    private final Long id;
    private final String title;

    public UpdateTaskConfigurationCommand(Long id, String title) {
	this.id = id;
	this.title = title;
    }

    public Long getId() {
	return id;
    }

    public String getTitle() {
	return title;
    }
}
