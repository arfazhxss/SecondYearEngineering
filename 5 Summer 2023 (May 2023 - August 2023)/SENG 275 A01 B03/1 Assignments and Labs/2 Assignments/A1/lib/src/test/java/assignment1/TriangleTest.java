package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TriangleTest {

    @ParameterizedTest
    @MethodSource("triangleTestCases")
    void testTriangleCategorization(String description, int x, int y, int z, Triangle.TriangleType expected) {
        Triangle.TriangleType result = Triangle.categorize(x, y, z);
        Assertions.assertEquals(expected, result, description);
    }

    private static Stream<Arguments> triangleTestCases() {
        return Stream.of(
                // Equilateral & Scalene Triangle Tests
                Arguments.of("Equilateral Triangle", 5, 5, 5, Triangle.TriangleType.EQUILATERAL),
                Arguments.of("SCALENE Triangle", 5, 6, 7, Triangle.TriangleType.SCALENE),

                // Equilateral & Isosceles Triangle  Tests
                Arguments.of("Equilateral Triangle", 5, 5, 5, Triangle.TriangleType.EQUILATERAL),
                Arguments.of("ISOSCELES Triangle", 5, 6, 5, Triangle.TriangleType.ISOSCELES),

                // Equilateral & Invalid Triangle Tests
                Arguments.of("Equilateral Triangle", 5, 5, 5, Triangle.TriangleType.EQUILATERAL),
                Arguments.of("Not Invalid", 5, 5, 11, Triangle.TriangleType.INVALID)
        );
    }
}
