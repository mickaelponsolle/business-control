package org.business.control.business.event;

import java.util.UUID;

import org.business.control.business.utils.Money;

public class CatalogTaskOfferedEvent extends Event {
    public UUID id;
    public String title;
    public Money price;
}
