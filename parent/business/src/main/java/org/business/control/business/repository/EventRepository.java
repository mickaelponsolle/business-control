package org.business.control.business.repository;

import org.business.control.business.event.BusinessEvent;

public interface EventRepository {
    public void store(BusinessEvent event);
}
