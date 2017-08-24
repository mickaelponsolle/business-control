package org.business.control.business;

import java.util.Arrays;

import org.business.control.business.aggregate.TaskConfiguration;
import org.business.control.business.bus.CommandBus;
import org.business.control.business.command.CreateTaskConfigurationCommand;
import org.business.control.business.event.store.EventStore;
import org.business.control.business.event.store.InMemoryEventStore;
import org.business.control.business.exception.aggregate.TaskConfigurationException;
import org.business.control.business.handler.CreateTaskConfigurationCommandHandler;
import org.business.control.business.utils.Money;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CreateTaskConfigurationTest {

    @Test
    public void whenCommandSentToCommandBusThenHandlerAppliedIt() {
        CreateTaskConfigurationCommand command = new CreateTaskConfigurationCommand("coupe", Money.of(7));

        CreateTaskConfigurationCommandHandler handler = Mockito.mock(CreateTaskConfigurationCommandHandler.class);
        Mockito.when(handler.listenTo()).thenCallRealMethod();
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));
        commandBus.dispatch(command);

        Mockito.verify(handler).handle(command);
    }

    @Test
    public void whenInvalidCommandWithNullTitleSentToCommandBusThenThrowException() {
        CreateTaskConfigurationCommand command = new CreateTaskConfigurationCommand(null, Money.of(7));

        CreateTaskConfigurationCommandHandler handler = new CreateTaskConfigurationCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (TaskConfigurationException e) {
            Assert.assertTrue(e.isNullTitle());
            Assert.assertFalse(e.isNullPrice());
            Assert.assertFalse(e.isNegativePrice());
        }
    }

    @Test
    public void whenInvalidCommandWithNullPriceSentToCommandBusThenThrowException() {
        CreateTaskConfigurationCommand command = new CreateTaskConfigurationCommand("coupe", null);

        CreateTaskConfigurationCommandHandler handler = new CreateTaskConfigurationCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (TaskConfigurationException e) {
            Assert.assertTrue(e.isNullPrice());
            Assert.assertFalse(e.isNegativePrice());
            Assert.assertFalse(e.isNullTitle());
        }

    }

    @Test
    public void whenInvalidCommandWithNegativePriceSentToCommandBusThenThrowException() {
        CreateTaskConfigurationCommand command = new CreateTaskConfigurationCommand("coupe", Money.of(-7));

        CreateTaskConfigurationCommandHandler handler = new CreateTaskConfigurationCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (TaskConfigurationException e) {
            Assert.assertFalse(e.isNullPrice());
            Assert.assertTrue(e.isNegativePrice());
            Assert.assertFalse(e.isNullTitle());
        }

    }

    @Test
    public void whenInvalidCommandWithMultipleErrorsSentToCommandBusThenThrowException() {
        CreateTaskConfigurationCommand command = new CreateTaskConfigurationCommand(null, Money.of(-7));

        CreateTaskConfigurationCommandHandler handler = new CreateTaskConfigurationCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (TaskConfigurationException e) {
            Assert.assertTrue(e.isNullTitle());
            Assert.assertTrue(e.isNegativePrice());
        }

    }

    @Test
    public void whenValidCommandSentToCommandBusThenEventIsStored() {
        EventStore eventStore = new InMemoryEventStore();
        CreateTaskConfigurationCommandHandler handler = new CreateTaskConfigurationCommandHandler(eventStore);
        handler.handle(new CreateTaskConfigurationCommand("coupe", Money.of(7)));
        Assert.assertEquals(1, eventStore.size());
        Assert.assertEquals(1, eventStore.get(TaskConfiguration.class).size());
        Assert.assertEquals(0, eventStore.get(Void.class).size());
    }
}
