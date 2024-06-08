package com.adriannebulao.money;

import java.util.Objects;

class Money {
    private final Currency currency;
    private final int dollars;
    private final int cents;
    private final boolean isNegative;

    Money(Currency currency, int dollars, int cents, boolean isNegative) {
        this.currency = currency;
        this.dollars = dollars;
        this.cents = cents;
        this.isNegative = isNegative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return dollars == money.dollars && cents == money.cents && isNegative == money.isNegative && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, dollars, cents, isNegative);
    }

    @Override
    public String toString() {
        return currency + " " + dollars + "." + cents;
    }
}

enum Currency {
    PHP, USD, EUR
}
