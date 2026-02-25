package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    /**
     * Рассчитывает итоговую цену со скидкой
     *
     * @param price    исходная цена (должна быть > 0)
     * @param discount процент скидки (от 0 до 100)
     * @return цена со скидкой
     */

    public double calculateDiscountedPrice(double price, double discount) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }

        double finalPrice = price - (price * discount / 100);
        // Округляем до двух знаков после запятой
        return Math.round(finalPrice * 100) / 100.0;
    }

    @Test
    @DisplayName("calculateDiscountedPrice не принимает discount = -1 ")
    void testDiscount_minusOne_throwsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculateDiscountedPrice(980, -1)
        );
    }

    @Test
    @DisplayName("calculateDiscountedPrice принимает discount = 0 ")
    void testDiscount_zero_returnsFullPrice() {
        double result = calculateDiscountedPrice(800, 0);
        double expected = 800;
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("calculateDiscountedPrice принимает discount = 1 ")
    void testDiscount_one_appliesCorrectly() {
        double result = calculateDiscountedPrice(1000, 1);
        double expected = 990;
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("calculateDiscountedPrice принимает discount = 50 ")
    void testDiscount_fifty_appliesCorrectly() {
        double result = calculateDiscountedPrice(2500, 50);
        double expected = 1250;
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("calculateDiscountedPrice принимает discount = 99 ")
    void testDiscount_ninetyNine_appliesCorrectly() {
        double result = calculateDiscountedPrice(5000, 99);
        double expected = 50;
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("calculateDiscountedPrice принимает discount = 100 ")
    void testDiscount_hundred_returnsZero() {
        double result = calculateDiscountedPrice(150, 100);
        double expected = 0;
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("calculateDiscountedPrice не принимает discount = 101 ")
    void testDiscount_oneHundredOne_throwsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculateDiscountedPrice(1000, 101)
        );
    }

    @Test
    @DisplayName("calculateDiscountedPrice не принимает отрицательный price ")
    void testPrice_negative_throwsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculateDiscountedPrice(-100, 50)
        );
    }

}


