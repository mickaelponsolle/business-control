package org.business.control.business.exception.aggregate;

import org.business.control.business.exception.command.CommandBusException;

public class CatalogTaskException extends RuntimeException implements CommandBusException {
    private static final long serialVersionUID = 6905203632465055284L;

    private boolean nullTitle;
    private boolean nullPrice;
    private boolean negativePrice;

    public boolean isNullTitle() {
        return nullTitle;
    }

    public void setNullTitle(boolean nullTitle) {
        this.nullTitle = nullTitle;
    }

    public boolean isNullPrice() {
        return nullPrice;
    }

    public void setNullPrice(boolean nullPrice) {
        this.nullPrice = nullPrice;
    }

    public boolean isNegativePrice() {
        return negativePrice;
    }

    public void setNegativePrice(boolean negativePrice) {
        this.negativePrice = negativePrice;
    }

}
