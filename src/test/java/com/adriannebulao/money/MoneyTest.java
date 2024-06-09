package com.adriannebulao.money;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static com.adriannebulao.money.Currency.*;

class MoneyTest {
    @Test
    void initialize_money_object_valid_arguments() {
        Money money = new Money(PHP, 1, 50, false);
        assertEquals(new Money(PHP, 1, 50, false), money);
    }

    @Test
    void initialize_money_object_null_currency() {
        assertThrows(NullPointerException.class, () -> new Money(null, 1, 50, false));
    }

    @Test
    void initialize_money_object_negative_dollars() {
        assertThrows(IllegalArgumentException.class, ()-> new Money(USD, -1, 50, false));
    }

    @Test
    void initialize_money_object_negative_cents() {
        assertThrows(IllegalArgumentException.class, ()-> new Money(EUR, 1, -50, false));
    }

    @Test
    void initialize_money_object_cents_greater_than_99() {
        assertThrows(IllegalArgumentException.class, ()-> new Money(PHP, 1, 100, false));
    }

    @ParameterizedTest
    @CsvSource({
            "PHP, 3, 0, false, 2, 0, false, 5, 0, false",
            "USD, 1, 50, false, 2, 10, false, 3, 60, false",
            "EUR, 2, 10, false,  3, 10, true, 1, 0, true",
            "PHP, 99, 99, false,  99, 99, false, 199, 98, false",
            "USD, 0, 50, false, 1, 0, true, 0, 50, true",
            "USD, 5, 50, false, 6, 50, true, 1, 0, true",
            "EUR, 5, 49, false, 4, 99, true, 0, 50, false",
            "PHP, 100, 99, true, 101, 99, false, 1, 0, false",
            "USD, 50, 50, true, 50, 50, true, 101, 0, true",
    })
    void add_money_same_currency(Currency currency, int dollars1, int cents1, boolean isNegative1,
                                 int dollars2, int cents2, boolean isNegative2, int expectedDollars,
                                 int expectedCents, boolean expectedIsNegative) {
        Money value1 = new Money(currency, dollars1, cents1, isNegative1);
        Money value2 = new Money(currency, dollars2, cents2, isNegative2);
        Money actual = value1.plus(value2);

        assertEquals(new Money(currency, expectedDollars, expectedCents, expectedIsNegative), actual);
    }
}
