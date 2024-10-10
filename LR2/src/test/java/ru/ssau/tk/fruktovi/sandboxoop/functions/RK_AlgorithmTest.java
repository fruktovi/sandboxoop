package ru.ssau.tk.fruktovi.sandboxoop.functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RK_AlgorithmTest {

    @Test
    public void testRungeKuttaBasic() {
        RK_Algorithm rk = new RK_Algorithm(0, 1, 0.1);
        double result = rk.apply(0.1);
        double expected = 1.10517; // Expected value (approximated)
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testRungeKuttaMultipleSteps() {
        RK_Algorithm rk = new RK_Algorithm(0, 1, 0.2);
        double result = rk.apply(0.2);
        double expected = 1.2214; // Expected value (approximated)
        assertEquals(expected, result, 0.05);
    }


}
