package org.business.control.business.event.store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.business.control.business.aggregate.CatalogTask;
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

    @Override
    public List<Event> get(Class<?> clazz) {
        return events.stream().filter(event -> event.getAggregateType().equals(clazz)).collect(Collectors.toList());
    }

    @Override
    public List<Event> get(Class<CatalogTask> clazz, UUID id) {
        return events.stream()
                .filter(event -> event.getAggregateType().equals(clazz) && event.getAggregateId().equals(id))
                .collect(Collectors.toList());
    }
}
