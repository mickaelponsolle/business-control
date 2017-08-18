package org.business.control.business2;

import java.util.Arrays;

import org.business.control.business2.bus.CommandBus;
import org.business.control.business2.command.CreateTaskConfigurationCommand;
import org.business.control.business2.handler.CreateTaskConfigurationHandler;
import org.business.control.business2.utils.Money;
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
    public void whenHandlerApplyCommandThenEventEmitted() {
        CreateTaskConfigurationHandler handler = new CreateTaskConfigurationHandler();
        handler.handle(new CreateTaskConfigurationCommand("coupe", Money.of(7)));
    }
}
