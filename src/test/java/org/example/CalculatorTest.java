package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorTest {
    static {
        // Подавляем предупреждение GraalVM интерпретатора
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
    }

    public static double calculate(String expression) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("graal.js");

        if (engine == null) {
            engine = manager.getEngineByName("JavaScript");
        }

        if (engine == null) {
            throw new RuntimeException("JavaScript движок не найден");
        }

        Object result = engine.eval(expression);

        // Безопасное преобразование в double
        if (result instanceof Number) {
            return ((Number) result).doubleValue();
        } else if (result instanceof String) {
            try {
                return Double.parseDouble((String) result);
            } catch (NumberFormatException e) {
                throw new ScriptException("Результат не является числом: " + result);
            }
        } else {
            throw new ScriptException("Неожиданный тип результата: " + result.getClass());
        }
    }

    @Test
    void bracketsTest1() throws ScriptException {
        double result = calculate("(2+3) * 4");
        double expected = (2 + 3) * 4;
        Assertions.assertEquals(result, expected);
    }

    @Test
    void additionTest2() throws ScriptException {
        double result = calculate("2+3");
        double expected = 2 + 3;
        Assertions.assertEquals(result, expected);
    }

    @Test
    void multiplicationTest3() throws ScriptException {
        double result = calculate("5*3");
        double expected = 5 * 3;
        Assertions.assertEquals(result, expected);
    }

    @Test
    void subtractionTest4() throws ScriptException {
        double result = calculate("10-5");
        double expected = 10 - 5;
        Assertions.assertEquals(result, expected);
    }

    @Test
    void divisionTest5() throws ScriptException {
        double result = calculate("100/5");
        double expected = 100 / 5;
        Assertions.assertEquals(result, expected);
    }

    @Test
    void degreeTest6() throws ScriptException {
        double result = calculate("2**3");
        double expected = 2 * 2 * 2;
        Assertions.assertEquals(result, expected);
    }

    @Test
    void remainderTest7() throws ScriptException {
        double result = calculate("20%6");
        double expected = 20 % 6;
        Assertions.assertEquals(result, expected);
    }


    @Test
    void Test8() throws ScriptException {
        double result = calculate("10/(1+4)");
        double expected = 10 / (1 + 4);
        Assertions.assertEquals(result, expected);
    }
}
