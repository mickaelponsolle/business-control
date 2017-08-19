package org.business.control.business2.handler;

import org.business.control.business2.aggregate.TaskConfiguration;
import org.business.control.business2.command.CreateTaskConfigurationCommand;

public class CreateTaskConfigurationHandler extends CommandHandler<Void, CreateTaskConfigurationCommand> {

    @Override
    public Void handle(CreateTaskConfigurationCommand command) {
        TaskConfiguration taskConfiguration = new TaskConfiguration(command.getTitle(), command.getPrice());
        return null;
    }

    @Override
    public Class listenTo() {
        return CreateTaskConfigurationCommand.class;
    }

}
