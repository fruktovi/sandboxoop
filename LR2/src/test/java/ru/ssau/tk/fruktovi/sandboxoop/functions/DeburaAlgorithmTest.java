package ru.ssau.tk.fruktovi.sandboxoop.functions;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeburaAlgorithmTest {
    @Test
    void apply() {
        DeburaAlgorithm obj = new DeburaAlgorithm(1, new double[]{8, 5, 7, 8,9,4,7,5,12,55,77}, new double[]{0, 5, 10, 20, 10, 7, 5, 78, 9}, 2);
        Assertions.assertEquals(-15, obj.apply(3));
    }
}

