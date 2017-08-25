package org.business.control.business;

import java.util.Arrays;
import java.util.UUID;

import org.business.control.business.aggregate.CatalogTask;
import org.business.control.business.bus.CommandBus;
import org.business.control.business.command.OfferCatalogTaskCommand;
import org.business.control.business.event.CatalogTaskOfferedEvent;
import org.business.control.business.event.store.EventStore;
import org.business.control.business.event.store.InMemoryEventStore;
import org.business.control.business.exception.aggregate.CatalogTaskException;
import org.business.control.business.handler.OfferCatalogTaskCommandHandler;
import org.business.control.business.utils.Money;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class OfferCatalogTaskTest {

    @Test
    public void whenCommandSentToCommandBusThenHandlerAppliedIt() {
        OfferCatalogTaskCommand command = new OfferCatalogTaskCommand("coupe", Money.of(7));

        OfferCatalogTaskCommandHandler handler = Mockito.mock(OfferCatalogTaskCommandHandler.class);
        Mockito.when(handler.listenTo()).thenCallRealMethod();
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));
        commandBus.dispatch(command);

        Mockito.verify(handler).handle(command);
    }

    @Test
    public void whenInvalidCommandWithNullTitleSentToCommandBusThenThrowException() {
        OfferCatalogTaskCommand command = new OfferCatalogTaskCommand(null, Money.of(7));

        OfferCatalogTaskCommandHandler handler = new OfferCatalogTaskCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (CatalogTaskException e) {
            Assert.assertTrue(e.isNullTitle());
            Assert.assertFalse(e.isNullPrice());
            Assert.assertFalse(e.isNegativePrice());
        }
    }

    @Test
    public void whenInvalidCommandWithNullPriceSentToCommandBusThenThrowException() {
        OfferCatalogTaskCommand command = new OfferCatalogTaskCommand("coupe", null);

        OfferCatalogTaskCommandHandler handler = new OfferCatalogTaskCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (CatalogTaskException e) {
            Assert.assertTrue(e.isNullPrice());
            Assert.assertFalse(e.isNegativePrice());
            Assert.assertFalse(e.isNullTitle());
        }

    }

    @Test
    public void whenInvalidCommandWithNegativePriceSentToCommandBusThenThrowException() {
        OfferCatalogTaskCommand command = new OfferCatalogTaskCommand("coupe", Money.of(-7));

        OfferCatalogTaskCommandHandler handler = new OfferCatalogTaskCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (CatalogTaskException e) {
            Assert.assertFalse(e.isNullPrice());
            Assert.assertTrue(e.isNegativePrice());
            Assert.assertFalse(e.isNullTitle());
        }

    }

    @Test
    public void whenInvalidCommandWithMultipleErrorsSentToCommandBusThenThrowException() {
        OfferCatalogTaskCommand command = new OfferCatalogTaskCommand(null, Money.of(-7));

        OfferCatalogTaskCommandHandler handler = new OfferCatalogTaskCommandHandler(null);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));

        try {
            commandBus.dispatch(command);
            Assert.assertTrue(false);
        } catch (CatalogTaskException e) {
            Assert.assertTrue(e.isNullTitle());
            Assert.assertTrue(e.isNegativePrice());
        }

    }

    @Test
    public void whenValidCommandSentToCommandBusThenEventIsStored() {
        OfferCatalogTaskCommand command = new OfferCatalogTaskCommand("coupe", Money.of(7));
        EventStore eventStore = new InMemoryEventStore();

        OfferCatalogTaskCommandHandler handler = new OfferCatalogTaskCommandHandler(eventStore);
        CommandBus commandBus = new CommandBus(Arrays.asList(handler));
        commandBus.dispatch(command);

        Assert.assertEquals(1, eventStore.size());
        Assert.assertEquals(1, eventStore.get(CatalogTask.class).size());
        Assert.assertEquals(0, eventStore.get(Void.class).size());
    }

    @Test
    public void givenSomeEventsInEventStoreWhenReconstructionAskedThenReconstructionDone() {
        CatalogTaskOfferedEvent catalogTask1Offered = new CatalogTaskOfferedEvent(UUID.randomUUID(), "coupe",
                Money.of(18));

        CatalogTaskOfferedEvent catalogTask2Offered = new CatalogTaskOfferedEvent(UUID.randomUUID(), "coupe/brushing",
                Money.of(24));

        EventStore eventStore = new InMemoryEventStore();
        eventStore.store(catalogTask1Offered);
        eventStore.store(catalogTask2Offered);

        CatalogTask catalogTask = new CatalogTask();
        CatalogTask catalogTaskRebuilded = catalogTask
                .apply(eventStore.get(CatalogTask.class, catalogTask1Offered.getAggregateId()));
        Assert.assertEquals(catalogTask1Offered.getTitle(), catalogTaskRebuilded.getTitle());
        Assert.assertEquals(catalogTask1Offered.getPrice(), catalogTaskRebuilded.getPrice());
        Assert.assertEquals(catalogTask1Offered.getAggregateId(), catalogTaskRebuilded.getId());

    }
}
