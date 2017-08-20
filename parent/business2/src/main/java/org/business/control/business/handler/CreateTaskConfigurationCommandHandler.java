package org.business.control.business.handler;

import org.business.control.business.aggregate.TaskConfiguration;
import org.business.control.business.command.CreateTaskConfigurationCommand;
import org.business.control.business.event.Event;
import org.business.control.business.event.store.EventStore;
import org.business.control.business.utils.Pair;

public class CreateTaskConfigurationCommandHandler extends CommandHandler<Void, CreateTaskConfigurationCommand> {

    public CreateTaskConfigurationCommandHandler(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    public Void handle(CreateTaskConfigurationCommand command) {

        TaskConfiguration taskConfiguration = new TaskConfiguration();
        Pair<TaskConfiguration, Event> pair = taskConfiguration.build(command.getTitle(), command.getPrice());
        eventStore.store(pair.getRight());

        return null;
    }

    @Override
    public Class listenTo() {
        return CreateTaskConfigurationCommand.class;
    }

}
