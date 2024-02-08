package assignment1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralTest {
    @ParameterizedTest
    @MethodSource("generator")
    void convert(String description, Object inputValues, Object expectedValue) {
        String[] inputs;
        int[] expectedResults;

        if (inputValues instanceof String[]) { inputs = (String[]) inputValues; }
        else { inputs = new String[]{(String) inputValues}; }
        if (expectedValue instanceof int[]) { expectedResults = (int[]) expectedValue; }
        else { expectedResults = new int[]{(int) expectedValue}; }

        for (int i = 0; i < Math.min(inputs.length, expectedResults.length); i++) {
            int expectedResult = expectedResults[i];
            int result = RomanNumeral.convert(inputs[i]);
            assertEquals(expectedResult, result);
        }
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of("Test Case 1: Letter Test", new String[] {"I","X","Z","!"}, new int[] {1,10,0,0}),
                Arguments.of("Test Case 2: Additive Combinations Test", new String[] {"III","LXVII","XXM","XXLC"}, new int[] {3,67,0,0}),
                Arguments.of("Test Case 3: Subtractive Combinations Test", new String[] {"IV","XCIX","DM","CDM"}, new int[] {4,99,0,0}),
                Arguments.of("Test Case 4: Maximum Roman Numeral Test", new String[] {"MMMCMXCIX","MMMM"}, new int[] {3999,0}),
                Arguments.of("Test Case 5: Invalid Combinations Test", new String[] {"ABC","X!V","123"}, new int[] {0,0,0}),
                Arguments.of("Test Case 6: Invalid Repetition Test", new String[] {"IIII","CCCCC"}, new int[] {0,0}),
                Arguments.of("Test Case 7: Invalid Subtractive Combinations", new String[] {"VV","DD","LL"}, new int[] {0,0,0}),
                Arguments.of("Test Case 8: Mixed Case Test", new String[] {"mcxi","IiX"}, new int[] {1111,0}),
                Arguments.of("Test Case 9: Mixed Complex Combinations", new String[] {"McmXlIv","mmMCMxcIx"}, new int[] {1944,3999}),
                Arguments.of("Test Case 10: AlphaNumerical Cases", new String[] {"0XX","CD2", "70G", "h^8"}, new int[] {0,0,0,0}),
                Arguments.of("Test Case 11: Empty and Null Inputs", new String[] {""," ", null}, new int[] {0,0,0}),
                Arguments.of("Test Case 12: Spacers", new String[] {"F HCF"," I", "I II"}, new int[] {0,1,0})
        );
    }
}