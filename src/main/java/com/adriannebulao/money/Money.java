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

        int totalDollars = calculateDollars(otherMoney);
        int totalCents = calculateCents(otherMoney);
        boolean isTotalNegative = this.isNegative == otherMoney.isNegative
                ? this.isNegative
                : totalDollars < 0;
        totalDollars = Math.abs(totalDollars);

        if (this.isNegative == otherMoney.isNegative) {
            totalDollars += totalCents / 100;
            totalCents = totalCents % 100;
        } else {
            if (totalCents < 0) {
                totalCents += 100;
                --totalDollars;
            }
        }
        return new Money(currency, totalDollars, totalCents, isTotalNegative);
    }

    Money minus(Money otherMoney) {
        return plus(new Money(otherMoney.currency, otherMoney.dollars, otherMoney.cents, !otherMoney.isNegative));
    }

    int calculateDollars(Money otherMoney) {
        int dollars1 = this.isNegative
                ? -(this.dollars)
                : this.dollars;
        int dollars2 = otherMoney.isNegative
                ? -(otherMoney.dollars)
                : otherMoney.dollars;
        return dollars1 + dollars2;
    }

    int calculateCents(Money otherMoney) {
        int totalCents;
        if (this.isNegative == otherMoney.isNegative) {
            totalCents = this.cents + otherMoney.cents;
        } else {
            totalCents = this.cents > otherMoney.cents
                    ? this.cents - (100 + otherMoney.cents)
                    : this.cents - otherMoney.cents;
        }
        return totalCents;
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
        StringBuilder centString = new StringBuilder(currency + " ");
        if (isNegative) {
            centString.append("-").append(dollars);
        } else {
            centString.append(dollars);
        }
        centString.append(".");
        if (cents < 10) {
            centString.append(0).append(cents);
        } else {
            centString.append(cents);
        }

        return centString.toString();
    }
}

enum Currency {
    PHP, USD, EUR
}
