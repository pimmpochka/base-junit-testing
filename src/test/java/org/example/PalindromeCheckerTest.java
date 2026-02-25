package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PalindromeCheckerTest {

    /**
     * Проверяет, является ли строка палиндромом.
     * Регистр символов игнорируется (приводится к нижнему).
     * Пустая строка и строка из одного символа считаются палиндромами.
     *
     * @param word строка для проверки (не должна быть null)
     * @return true, если слово палиндром, иначе false
     */
    boolean isPalindrome(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word cannot be null");
        }
        String cleaned = word.toLowerCase(); // игнорируем регистр
        int left = 0;
        int right = cleaned.length() - 1;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Test
    @DisplayName("isPalindrome вернет true, если слово является полиндромом")
    void isPalindromeOk() {
        boolean result = isPalindrome("радар");
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("isPalindrome вернет true, если число является полиндромом")
    void isPalindromeNumber() {
        boolean result = isPalindrome(String.valueOf(121));
        Assertions.assertTrue(result);
    }

    //Проверяем, что пробелы учитываются
    @Test
    @DisplayName("isPalindrome при вводе предложений вернет true")
    void isPalindromeOffer() {
        boolean result = isPalindrome("Лёша на полке клопа нашёл");
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("isPalindrome при вводе слов не полиндромов вернет true")
    void isPalindromeWord() {
        boolean result = isPalindrome("хлеб");
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("isPalindrome при вводе чисел не полиндромов вернет true")
    void isPalindromeNum() {
        boolean result = isPalindrome(String.valueOf(123));
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("isPalindrome при вводе пустой строки вернет true")
    void isPalindromeEmptyString() {
        boolean result = isPalindrome("");
        Assertions.assertTrue(result);
    }


    @Test
    @DisplayName("isPalindrome не принимает значение NULL")
    void isPalindromeNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            isPalindrome(null);
        });
    }

}


