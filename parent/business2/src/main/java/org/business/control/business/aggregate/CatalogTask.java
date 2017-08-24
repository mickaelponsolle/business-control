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

    public Pair<CatalogTask, Event> build(String title, Money price) {
        CatalogTask catalogTask = new CatalogTask();
        catalogTask.id = UUID.randomUUID();
        catalogTask.title = title;
        catalogTask.price = price;

        checkCommandValidity(title, price);

        Event offerCatalogTaskEvent = new Event();
        offerCatalogTaskEvent.aggregateType = catalogTask.getClass();

        return new Pair<CatalogTask, Event>(catalogTask, offerCatalogTaskEvent);
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

    public void apply(List<Event> events) {
        for (Event event : events) {
            if (event.eventType.equals(CatalogTaskOfferedEvent.class.getName())) {

            }

        }

    }
}
