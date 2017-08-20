package org.business.control.business.event.store;

import java.util.ArrayList;
import java.util.List;

import org.business.control.business.event.Event;

public class InMemoryEventStore implements EventStore {

    List<Event> events = new ArrayList<Event>();

    @Override
    public int size() {
        return events.size();
    }

    @Override
    public List<Event> getAll() {
        return events;
    }

    @Override
    public void store(Event event) {
        events.add(event);
    }

}
