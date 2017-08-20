package org.business.control.business.utils;

import java.math.BigDecimal;

public class Money {

    private BigDecimal value;

    private Money(int value) {
        this.value = new BigDecimal(value);
    }

    public static Money of(int value) {
        return new Money(value);
    }

    public BigDecimal getValue() {
        return value;
    }
}
