package org.business.control.business.event;

import java.time.LocalDateTime;

public class Event {
    public LocalDateTime eventDateTime = LocalDateTime.now();
    public Class<?> aggregateType;

}
