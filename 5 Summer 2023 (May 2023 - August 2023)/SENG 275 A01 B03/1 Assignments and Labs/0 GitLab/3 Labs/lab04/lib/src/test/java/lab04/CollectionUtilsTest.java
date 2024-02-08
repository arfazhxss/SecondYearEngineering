package lab04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class CollectionUtilsTest {

    @ParameterizedTest
    @MethodSource("generator")
    void containsAny(String description, Collection<?> coll1, Collection<?> coll2, boolean expected) {
        boolean result = CollectionUtils.containsAny(coll1, coll2);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of("non-empty intersection similar size", new HashSet<>(Arrays.asList(1, 2, 3)), new HashSet<>(Arrays.asList(3, 4, 5)), true),
                Arguments.of("non-empty intersection non-similar size", new HashSet<>(Arrays.asList(2, 3)), new HashSet<>(Arrays.asList(3, 4, 5)), true),
                Arguments.of("empty intersection", new HashSet<>(Arrays.asList(1, 2, 3)), new HashSet<>(Arrays.asList(4, 5, 6)), false),
                Arguments.of("empty collections", new HashSet<>(), new HashSet<>(), false)
        );
    }
}
