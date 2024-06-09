package com.adriannebulao.money;

import java.util.Objects;

class Money {
    private final Currency currency;
    private final int dollars;
    private final int cents;
    private final boolean isNegative;

    Money(Currency currency, int dollars, int cents, boolean isNegative) {
        moneyValidation(currency, cents);
        this.currency = currency;
        this.dollars = Math.abs(dollars);
        this.cents = cents;
        this.isNegative = isNegative;
    }

    Money plus(Money otherMoney) {
        currencyMismatchValidation(currency, otherMoney.currency);

        int totalDollars;
        int totalCents;
        boolean isTotalNegative;

        if (this.isNegative == otherMoney.isNegative) {
            totalDollars = this.dollars + otherMoney.dollars;
            totalCents = this.cents + otherMoney.cents;
            isTotalNegative = this.isNegative;
            totalDollars += totalCents / 100;
        } else {
            int dollars1 = this.isNegative
                    ? -(this.dollars)
                    : this.dollars;
            int dollars2 = otherMoney.isNegative
                    ? -(otherMoney.dollars)
                    : otherMoney.dollars;
            totalDollars = dollars1 + dollars2;
            totalCents = this.cents > otherMoney.cents
                    ? this.cents - (100 + otherMoney.cents)
                    : this.cents - otherMoney.cents;
            isTotalNegative = totalDollars < 0;
            if (totalCents < 0) {
                if (isTotalNegative) {
                    ++totalDollars;
                } else {
                    --totalDollars;
                }
            }
        }
        totalCents = Math.abs(totalCents % 100);
        return new Money(currency, totalDollars, totalCents, isTotalNegative);
    }

    void moneyValidation(Currency currency, int cents) {
        Objects.requireNonNull(currency, "currency must not be null");
        if (cents < 0 || cents > 99) {
            throw new IllegalArgumentException("cents must be between 0 and 99, was: " + cents);
        }
    }

    void currencyMismatchValidation(Currency currency, Currency otherCurrency) {
        if (currency != otherCurrency) {
            throw new CurrencyMismatchException("cannot operate on money of different currencies");
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
