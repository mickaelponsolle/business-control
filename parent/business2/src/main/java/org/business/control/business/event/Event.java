package org.business.control.business.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    public LocalDateTime eventDateTime = LocalDateTime.now();
    public Class<?> aggregateType;
    public String eventType;
    public UUID aggregateId;

}
