package org.business.control.business.handler;

import org.business.control.business.event.store.EventStore;

public abstract class CommandHandler<R, C> extends Handler<R, C> {
    protected EventStore eventStore;

    public CommandHandler() {

    }

    public CommandHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }
}
