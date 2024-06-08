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
}
