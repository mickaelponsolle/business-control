package org.business.control.business.command;

import org.business.control.business.utils.Money;

public class CreateTaskConfigurationCommand implements Command<Void> {

    private final String title;
    private final Money price;

    public CreateTaskConfigurationCommand(String title, Money price) {
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
