package ru.ssau.tk.fruktovi.sandboxoop.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.fruktovi.sandboxoop.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.ArrayTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.TabulatedFunction;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.fruktovi.sandboxoop.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {
    private TabulatedFunction tabulatedFunction;
    private TabulatedDifferentialOperator differentialOperator;
    private TabulatedFunction baseFunction;
    private SynchronizedTabulatedFunction synchronizedFunction;
    private TabulatedFunction derivedFunction;

    private TabulatedFunction createArrayTabulatedFunction() {
        return new ArrayTabulatedFunction(new double[]{-3, 1.5, 6, 10.5, 15}, new double[]{9, 2.25, 36, 110.25, 225});
    }

    private TabulatedFunction createLinkedListTabulatedFunction() {
        return new LinkedListTabulatedFunction(new double[]{-3, 1.5, 6, 10.5, 15}, new double[]{9, 2.25, 36, 110.25, 225});
    }

        

    @Test
    void testConstructor1(){
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, operator.getFactory());
    }

    @Test
    void testConstructor2(){
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        assertInstanceOf(ArrayTabulatedFunctionFactory.class, operator.getFactory());
    }

    @Test
    void testSet(){
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        operator.setFactory(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, operator.getFactory());
    }



    @Test
    void testDeriveWithNewArrayFunction() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction function = operator.derive(new ArrayTabulatedFunction(x -> Math.pow(x, 2) + x + 1, -2, 1, 4));

        // Ожидаемые значения на основе пересчитанных производных
        double[] xValues = new double[] {-2.0, -1.25, -0.5, 0.25};
        double[] yValues = new double[] {-3.0, -1.5, 0.0, 1.5};

        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(xValues[i], function.getX(i), 10);
            assertEquals(yValues[i], function.getY(i), 10);
        }
    }
    @Test
    void testDeriveSynchronously() {
        tabulatedFunction = createLinkedListTabulatedFunction();
        differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction derivedFunction = differentialOperator.deriveSynchronously(tabulatedFunction);

        assertEquals(12, derivedFunction.apply(6), 0.01);
    }

}