package org.business.control.business2;

import java.util.Arrays;

import org.business.control.business2.bus.CommandBus;
import org.business.control.business2.command.CreateTaskConfigurationCommand;
import org.business.control.business2.exception.aggregate.TaskConfigurationException;
import org.business.control.business2.handler.CreateTaskConfigurationHandler;
import org.business.control.business2.utils.Money;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CreateTaskConfigurationTest {

    @Test
    public void whenCommandSentToCommandBusThenHandlerAppliedIt() {
        CreateTaskConfigurationCommand command = new CreateTaskConfigurationCommand("coupe", Money.of(7));

        CreateTaskConfigurationHandler handler = Mockito.mock(CreateTaskConfigurationHandler.class);
        Mockito.when(handler.listenTo()).thenCallRealMethod();
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));
        commandBus.dispatch(command);

        Mockito.verify(handler).handle(command);
    }

    @Test
    public void whenInvalidCommandWithNullTitleSentToCommandBusThenThrowException() {
        CreateTaskConfigurationCommand command = new CreateTaskConfigurationCommand(null, Money.of(7));

        CreateTaskConfigurationHandler handler = new CreateTaskConfigurationHandler();
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

        CreateTaskConfigurationHandler handler = new CreateTaskConfigurationHandler();
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

        CreateTaskConfigurationHandler handler = new CreateTaskConfigurationHandler();
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

        CreateTaskConfigurationHandler handler = new CreateTaskConfigurationHandler();
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
    public void whenHandlerApplyCommandThenAggregateIsInitialized() {
        CreateTaskConfigurationHandler handler = new CreateTaskConfigurationHandler();
        handler.handle(new CreateTaskConfigurationCommand("coupe", Money.of(7)));
    }
}
