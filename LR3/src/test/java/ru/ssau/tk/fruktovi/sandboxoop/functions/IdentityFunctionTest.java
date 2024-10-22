package ru.ssau.tk.fruktovi.sandboxoop.functions;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdentityFunctionTest {
    @Test
    void testApply() {
        IdentityFunction identityFunction = new IdentityFunction();
        Assertions.assertEquals(1, identityFunction.apply(1));
        Assertions.assertEquals(10.34, identityFunction.apply(10.34));
    }



    @Test
    void testToString() {
        IdentityFunction function = new IdentityFunction();
        Assertions.assertEquals("IdentityFunction: f(x) = x", function.toString());
    }

    @Test
    void testEquals() {
        IdentityFunction function1 = new IdentityFunction();
        IdentityFunction function2 = new IdentityFunction();
        Assertions.assertTrue(function1.equals(function2));
        Assertions.assertTrue(function1.equals(function1));
        Assertions.assertFalse(function1.equals(null));
        Assertions.assertFalse(function1.equals(new Object()));
    }

    @Test
    void testHashCode() {
        IdentityFunction function1 = new IdentityFunction();
        IdentityFunction function2 = new IdentityFunction();
        Assertions.assertEquals(function1.hashCode(), function2.hashCode());
    }

    @Test
    void testClone() {
        IdentityFunction function1 = new IdentityFunction();
        IdentityFunction function2 = function1.clone();
        Assertions.assertEquals(function1, function2);
        Assertions.assertNotSame(function1, function2); // Проверка, что это разные объекты
    }
}