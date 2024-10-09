package ru.ssau.tk.fruktovi.sandboxoop.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RK_AlgorithmTest {

    @Test
    void testRK_Algorithm() {
        RK_Algorithm rk = new RK_Algorithm();

        // Параметры для теста
        double x = 0; // Тестируемое значение
        double expectedValue = 1.10517019; // Ожидаемое значение y при x = 0.1 для y' = y
        double delta = 1e-5; // Допустимая ошибка

        // Получаем значение y
        double result = rk.apply(x);

        // Проверяем результат
        assertEquals(expectedValue, result, delta);
    }
}