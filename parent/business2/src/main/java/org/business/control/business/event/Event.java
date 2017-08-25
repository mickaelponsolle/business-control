package org.business.control.business.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    protected LocalDateTime eventDateTime = LocalDateTime.now();
    protected Class<?> aggregateType;
    protected Class<?> eventType;
    protected UUID aggregateId;

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public Class<?> getAggregateType() {
        return aggregateType;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

}
