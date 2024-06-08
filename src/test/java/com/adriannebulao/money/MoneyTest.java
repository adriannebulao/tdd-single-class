package com.adriannebulao.money;

import org.junit.jupiter.api.Test;

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
}
