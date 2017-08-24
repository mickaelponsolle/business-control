package org.business.control.business.handler;

import org.business.control.business.aggregate.CatalogTask;
import org.business.control.business.command.OfferCatalogTaskCommand;
import org.business.control.business.event.Event;
import org.business.control.business.event.store.EventStore;
import org.business.control.business.utils.Pair;

public class OfferCatalogTaskCommandHandler extends CommandHandler<Void, OfferCatalogTaskCommand> {

    public OfferCatalogTaskCommandHandler(EventStore eventStore) {
        super(eventStore);
    }

    @Override
    public Void handle(OfferCatalogTaskCommand command) {

        CatalogTask catalogTask = new CatalogTask();
        Pair<CatalogTask, Event> pair = catalogTask.build(command.getTitle(), command.getPrice());
        eventStore.store(pair.getRight());

        return null;
    }

    @Override
    public Class listenTo() {
        return OfferCatalogTaskCommand.class;
    }

}
