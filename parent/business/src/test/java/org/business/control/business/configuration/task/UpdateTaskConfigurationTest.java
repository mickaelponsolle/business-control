package org.business.control.business.configuration.task;

import org.business.control.business.aggregate.configuration.business.TaskConfigurationAggregate;
import org.business.control.business.command.configuration.task.UpdateTaskConfigurationCommand;
import org.business.control.business.event.BusinessEvent;
import org.business.control.business.repository.EventRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdateTaskConfigurationTest {

    @Mock
    private EventRepository eventRepository;

    @Test
    public void whenUpdateTaskConfigurationCommandThenEventStored() {
	UpdateTaskConfigurationCommand command = new UpdateTaskConfigurationCommand(3L, "coupe");
	Assert.assertNotNull(command.getId());
	Assert.assertEquals(Long.valueOf(3), command.getId());
	Assert.assertNotNull(command.getLibelle());
	Assert.assertFalse(command.getLibelle().isEmpty());

	TaskConfigurationAggregate taskConfigurationAggregate = new TaskConfigurationAggregate(eventRepository);
	taskConfigurationAggregate.apply(command);

	BusinessEvent businessEvent = new BusinessEvent(command);
	Mockito.verify(eventRepository).store(Mockito.refEq(businessEvent, "dateTime"));
    }
}
