package org.business.control.business2.handler;

import org.business.control.business2.command.CreateTaskConfigurationCommand;

public class CreateTaskConfigurationHandler extends CommandHandler<Void, CreateTaskConfigurationCommand> {

    @Override
    public Void handle(CreateTaskConfigurationCommand command) {
        return null;
    }

    @Override
    public Class listenTo() {
        return CreateTaskConfigurationCommand.class;
    }

}
