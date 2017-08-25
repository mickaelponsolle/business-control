package org.business.control.business.aggregate;

import java.util.List;
import java.util.UUID;

import org.business.control.business.event.CatalogTaskOfferedEvent;
import org.business.control.business.event.Event;
import org.business.control.business.exception.aggregate.CatalogTaskException;
import org.business.control.business.utils.Money;
import org.business.control.business.utils.Pair;

public class CatalogTask extends Aggregate {

    private UUID id;

    private String title;
    private Money price;

    public CatalogTask() {

    }

    public CatalogTask(CatalogTask other) {
        this.id = other.getId();
        this.title = other.getTitle();
        this.price = other.getPrice();
    }

    public Pair<CatalogTask, Event> build(String title, Money price) {
        checkCommandValidity(title, price);

        CatalogTaskOfferedEvent catalogTaskOfferedEvent = new CatalogTaskOfferedEvent(UUID.randomUUID(), title, price);

        CatalogTask catalogTask = this.applyCatalogTaskOfferedEvent(this, catalogTaskOfferedEvent);

        return new Pair<CatalogTask, Event>(catalogTask, catalogTaskOfferedEvent);
    }

    private void checkCommandValidity(String title, Money price) {
        CatalogTaskException exception = null;
        if (title == null) {
            if (exception == null) {
                exception = new CatalogTaskException();
            }
            exception.setNullTitle(true);
        }
        if (price == null) {
            if (exception == null) {
                exception = new CatalogTaskException();
            }
            exception.setNullPrice(true);
        } else if (price.getValue().signum() < 0) {
            if (exception == null) {
                exception = new CatalogTaskException();
            }
            exception.setNegativePrice(true);
        }
        if (exception != null) {
            throw exception;
        }
    }

    public CatalogTask apply(List<Event> events) {
        CatalogTask catalogTask = new CatalogTask(this);
        for (Event event : events) {
            if (event.getEventType().equals(CatalogTaskOfferedEvent.class)) {
                CatalogTaskOfferedEvent catalogTaskOfferedEvent = (CatalogTaskOfferedEvent) event;

                catalogTask = applyCatalogTaskOfferedEvent(catalogTask, catalogTaskOfferedEvent);
            }
        }
        return catalogTask;
    }

    private CatalogTask applyCatalogTaskOfferedEvent(CatalogTask catalogTask,
            CatalogTaskOfferedEvent catalogTaskOfferedEvent) {
        CatalogTask catalogTaskCopy = new CatalogTask(catalogTask);
        catalogTaskCopy.id = catalogTaskOfferedEvent.getAggregateId();
        catalogTaskCopy.title = catalogTaskOfferedEvent.getTitle();
        catalogTaskCopy.price = catalogTaskOfferedEvent.getPrice();
        return catalogTaskCopy;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Money getPrice() {
        return price;
    }
}
