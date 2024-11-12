package com.example.optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModemTest {
    @Test
    public void whenFiltersWithoutOptional_thenCorrect() {
        assertTrue(Modem.priceIsInRange1(new Modem(10.0)));
        assertFalse(Modem.priceIsInRange1(new Modem(9.9)));
        assertFalse(Modem.priceIsInRange1(new Modem(null)));
        assertFalse(Modem.priceIsInRange1(new Modem(15.5)));
        assertFalse(Modem.priceIsInRange1(null));
    }

    @Test
    public void whenFiltersWithOptional_thenCorrect() {
        assertTrue(Modem.priceIsInRange2(new Modem(10.0)));
        assertFalse(Modem.priceIsInRange2(new Modem(9.9)));
        assertFalse(Modem.priceIsInRange2(new Modem(null)));
        assertFalse(Modem.priceIsInRange2(new Modem(15.5)));
        assertFalse(Modem.priceIsInRange2(null));
    }
}
