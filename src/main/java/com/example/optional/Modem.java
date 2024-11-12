package com.example.optional;

import java.util.Optional;

import lombok.Value;

@Value
public class Modem {
    private Double price;

    public static boolean priceIsInRange1(Modem modem) {
        boolean isInRange = false;

        if (modem != null && modem.getPrice() != null
                && (modem.getPrice() >= 10
                        && modem.getPrice() <= 15)) {

            isInRange = true;
        }
        return isInRange;
    }

    public static boolean priceIsInRange2(Modem modem2) {
        return Optional.ofNullable(modem2)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }

}
