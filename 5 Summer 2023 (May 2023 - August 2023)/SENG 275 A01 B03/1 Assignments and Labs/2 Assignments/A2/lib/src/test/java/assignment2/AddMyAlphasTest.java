package assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddMyAlphasTest {
    @Test
    public void AddMyAlphaTest01_Q1_EmptyString() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("");
        assertEquals(0, result);
    }

    @Test
    public void AddMyAlphaTest02_Q1_OneDigits() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("1");
        assertEquals(1, result);
    }

    @Test
    public void AddMyAlphaTest03_Q1_TwoDigits() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("1,4");
        assertEquals(5, result);
    }

    @Test
    public void AddMyAlphaTest04_Q2_MultipleDigits() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("1,3,5,7,9,11,13,15,17,19");
        assertEquals(100, result);
    }

    @Test
    public void AddMyAlphaTest05_Q3_DoubleNewLineTest() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("1\n2,3");
        assertEquals(6, result);
    }

    @Test
    public void AddMyAlphaTest06_Q4_MultipleNegativeNumbers() {
        AddMyAlphas adder = new AddMyAlphas();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            adder.Add("2,-4,3,-5");
        });
        assertEquals("Negatives not allowed: [-4, -5]", exception.getMessage());
    }

    @Test
    public void AddMyAlphaTest07_Q5_NumbersBiggerNumbers() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("1,2\n2000\n3000");
        assertEquals(3, result);
    }
    @Test
    public void AddMyAlphaTest08_Q6_CustomDelimiterSemicolon() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("//;\n1;2");
        assertEquals(3, result);
    }

    @Test
    public void AddMyAlphaTest09_Q6_CustomDelimiterPipe() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("//\\|\n1|2|3");
        assertEquals(6, result);
    }

    @Test
    public void AddMyAlphaTest10_Q6_CustomDelimiterDollar() {
        AddMyAlphas adder = new AddMyAlphas();
        int result = adder.Add("//$\n1$2$3$4$5");
        assertEquals(15, result);
    }
}
