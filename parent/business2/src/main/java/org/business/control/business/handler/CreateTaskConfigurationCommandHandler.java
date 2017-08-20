package org.business.control.business.handler;

import org.business.control.business.aggregate.TaskConfiguration;
import org.business.control.business.command.CreateTaskConfigurationCommand;
import org.business.control.business.event.Event;
import org.business.control.business.event.store.EventStore;

public class CreateTaskConfigurationCommandHandler extends CommandHandler<Void, CreateTaskConfigurationCommand> {

    public CreateTaskConfigurationCommandHandler(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    public Void handle(CreateTaskConfigurationCommand command) {

        TaskConfiguration taskConfiguration = TaskConfiguration.build(command.getTitle(), command.getPrice());

        Event createTaskConfigurationEvent = new Event();
        createTaskConfigurationEvent.aggregateType = taskConfiguration.getClass();
        eventStore.store(createTaskConfigurationEvent);

        return null;
    }

    @Override
    public Class listenTo() {
        return CreateTaskConfigurationCommand.class;
    }

}
