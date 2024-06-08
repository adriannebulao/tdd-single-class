package com.adriannebulao.money;

import java.util.Objects;

class Money {
    private final Currency currency;
    private final int dollars;
    private final int cents;
    private final boolean isNegative;

    Money(Currency currency, int dollars, int cents, boolean isNegative) {
        moneyValidation(currency, dollars, cents);
        this.currency = currency;
        this.dollars = dollars;
        this.cents = cents;
        this.isNegative = isNegative;
    }

    void moneyValidation(Currency currency, int dollars, int cents) {
        Objects.requireNonNull(currency, "currency must not be null");
        if (dollars < 0) {
            throw new IllegalArgumentException("dollars must be positive, was: " + dollars);
        }
        if (cents < 0 || cents > 99) {
            throw new IllegalArgumentException("cents must be between 0 and 99, was: " + cents);
        }
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
