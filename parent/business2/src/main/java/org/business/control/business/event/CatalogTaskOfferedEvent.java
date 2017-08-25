package org.business.control.business.event;

import java.util.UUID;

import org.business.control.business.aggregate.CatalogTask;
import org.business.control.business.utils.Money;

public class CatalogTaskOfferedEvent extends Event {
    private String title;
    private Money price;

    public CatalogTaskOfferedEvent(UUID aggregateId, String title, Money price) {
        this.aggregateType = CatalogTask.class;
        this.eventType = this.getClass();
        this.aggregateId = aggregateId;
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Money getPrice() {
        return price;
    }
}
