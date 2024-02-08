package a3;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testComplexAdditionWithNegativeResult() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(-1, 10);
        assert -9 == result;
    }

    @Test
    public void testComplexAdditionWithPositiveResult() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(3, 7);
        assert 10 == result;
    }

    @Test
    public void testComplexAdditionWithZeroResult() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(-2, 2);
        assert 0 == result;
    }

    @Test
    public void testComplexAdditionWithBoundaryCondition() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(1, 1);
        assert -2 == result;
    }

    @Test
    public void testComplexAdditionWithEmptyReturn() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(5, 5);
        assert 10 == result;
    }

    @Test
    public void testComplexAdditionWithFalseReturn() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(0, 0);
        assert 0 == result;
    }

    @Test
    public void testComplexAdditionWithIncrement() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(10, 1);
        assert 11 == result;
    }

    @Test
    public void testComplexAdditionWithInvertedNegative() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(3, -3);
        assert 0 == result;
    }

    @Test
    public void testComplexAdditionWithIncorrectMath() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(2, 3);
        assert 5 == result;
    }

    @Test
    public void testComplexAdditionWithNegatedConditional() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(5, 0);
        assert 5 == result;
    }

    @Test
    public void testComplexAdditionWithNullReturn() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(4, 2);
        assert 6 == result;
    }

    @Test
    public void testComplexAdditionWithPrimitiveReturn() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(7, 3);
        assert 10 == result;
    }

    @Test
    public void testComplexAdditionWithTrueReturn() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(0, 0);
        assert 0 == result;
    }

    @Test
    public void testComplexAdditionWithVoidMethodCall() {
        Calculator calculator = new Calculator();
        int result = calculator.ComplexAdd(6, 2);
        assert 8 == result;
    }
}