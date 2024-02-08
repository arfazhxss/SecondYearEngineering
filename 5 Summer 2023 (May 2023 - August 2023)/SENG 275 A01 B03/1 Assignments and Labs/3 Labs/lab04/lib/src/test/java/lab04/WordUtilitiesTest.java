package lab04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class WordUtilitiesTest {

    @ParameterizedTest
    @MethodSource("generator")
    void swapCase(String description, String input, String expected) {
        String result = WordUtilities.swapCase(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of("null string", null, null),
                Arguments.of("empty string", "", ""),
                Arguments.of("string with all lowercase letters", "hello", "HELLO"),
                Arguments.of("string with all uppercase letters", "WORLD", "world"),
                Arguments.of("string with mixed case letters", "Hello World", "hELLO wORLD"),
                Arguments.of("string with special characters", "!@#$%^&*", "!@#$%^&*")
        );
    }
}
