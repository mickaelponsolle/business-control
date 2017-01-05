package org.business.control.business.command.configuration.task;

import org.business.control.business.command.BusinessCommand;

public class DeleteTaskConfigurationCommand implements BusinessCommand {
    private final Long id;

    public DeleteTaskConfigurationCommand(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

}
