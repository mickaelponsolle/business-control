package org.business.control.business.configuration.task;

import org.business.control.business.aggregate.configuration.business.TaskConfigurationAggregate;
import org.business.control.business.command.configuration.task.AddTaskConfigurationCommand;
import org.business.control.business.event.BusinessEvent;
import org.business.control.business.repository.EventRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddTaskConfigurationTest {

    @Mock
    private EventRepository eventRepository;

    @Test
    public void whenAddTaskConfigurationCommandThenEventStored() {
        AddTaskConfigurationCommand command = new AddTaskConfigurationCommand("coupe");
        Assert.assertNotNull(command.getTitle());
        Assert.assertFalse(command.getTitle().isEmpty());

        TaskConfigurationAggregate taskConfigurationAggregate = new TaskConfigurationAggregate(eventRepository);
        taskConfigurationAggregate.apply(command);

        BusinessEvent businessEvent = new BusinessEvent(command);
        Mockito.verify(eventRepository).store(Mockito.refEq(businessEvent, "dateTime"));
    }

}
