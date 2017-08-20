package org.business.control.business.event.store;

import java.util.List;

import org.business.control.business.event.Event;

public interface EventStore {
    public int size();

    public List<Event> getAll();

    public void store(Event event);
}
