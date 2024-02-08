package lab03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BoundaryTest {

    @Test
    void isUnsafe() {
        assertTrue(Boundary.isUnsafe(86));
    }

    @Test
    void isNotUnsafe() {
        assertFalse(Boundary.isUnsafe(85));
    }

    @ParameterizedTest(name="storeys{0},IDs{1}")
    @CsvSource({"0,0","1,0","2,1","4,1","5,1","6,2","10,2"})
    void correctElevators(int storeys, int IDs) {
        if (IDs==0) { assertEquals(0, Boundary.elevatorsRequired (storeys)); }
        else if (IDs==1) { assertEquals(1, Boundary.elevatorsRequired (storeys)); }
        else if (IDs==2) { assertEquals(2, Boundary.elevatorsRequired (storeys)); }
    }

    @ParameterizedTest(name="storeys{0},IDs{1}")
    @CsvSource({"0,0","1,0","2,1","4,1","5,1","6,2","10,2"})
    void inCorrectElevators(int storeys, int IDs) {
        if (IDs==0) {
            assertNotEquals(1,Boundary.elevatorsRequired(storeys));
            assertNotEquals(2,Boundary.elevatorsRequired(storeys));
        }
        else if (IDs==1) {
            assertNotEquals(0, Boundary.elevatorsRequired(storeys));
            assertNotEquals(2,Boundary.elevatorsRequired(storeys));
        }
        else if (IDs==2) {
            assertNotEquals(0, Boundary.elevatorsRequired(storeys));
            assertNotEquals(1,Boundary.elevatorsRequired(storeys));
        }
    }

    @ParameterizedTest(name="Grade{0},corPercent{1}")
    @CsvSource({"A+,90","A,89","A-,84","A,85","A-,80","B+,77","B,76","B,73","B-,72","B-,70","C+,69","C+,65","C,64","C,60","D,59","D,50","F,49","N,-1","G,101","R,110"}) 
    void correctGrade (String Grade, double corPercent) {
        if (corPercent>= 0 && corPercent <= 100) { assertEquals(Grade, Boundary.percentageToLetterGrade(corPercent)); }
        else {
            assertThrows(IllegalArgumentException.class,() -> Boundary.percentageToLetterGrade(corPercent));
        }
    }

    @ParameterizedTest
    @ValueSource(ints={-1,4,5,19,20,50})
    void correctTemperature(int CorTemperature) {
        if (CorTemperature<5 || CorTemperature>20) {
            assertFalse(Boundary.isComfortable(CorTemperature));
        } else {
            assertTrue(Boundary.isComfortable(CorTemperature));
        }
    }
}