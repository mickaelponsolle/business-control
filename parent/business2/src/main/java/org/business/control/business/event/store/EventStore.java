package org.business.control.business.event.store;

import java.util.List;
import java.util.UUID;

import org.business.control.business.aggregate.CatalogTask;
import org.business.control.business.event.Event;

public interface EventStore {
    public int size();

    public List<Event> getAll();

    public void store(Event event);

    public List<Event> get(Class<?> clazz);

    public List<Event> get(Class<CatalogTask> clazz, UUID id);
}
