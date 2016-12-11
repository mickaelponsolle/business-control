package org.business.control.business.aggregate;

import org.business.control.business.repository.EventRepository;

public abstract class AbstractAggregate {
    protected EventRepository eventRepository;

    public AbstractAggregate(EventRepository eventRepository) {
	this.eventRepository = eventRepository;
    }
}
