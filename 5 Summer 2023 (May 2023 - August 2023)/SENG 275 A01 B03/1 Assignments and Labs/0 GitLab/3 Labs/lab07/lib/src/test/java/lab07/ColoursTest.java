package lab07;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import net.jqwik.api.*;
import net.jqwik.api.arbitraries.*;
import net.jqwik.api.constraints.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class ColoursTest {

    @Property
    void validRGBBytes (@ForAll @IntRange(min = 0, max = 255) int r,
                       @ForAll @IntRange(min = 0, max = 255) int g,
                       @ForAll @IntRange(min = 0, max = 255) int b) {

        int expected = r * 256 + g * 16 + b;
        assertThat(Colours.rgbBytesToInt(r, g, b)).isEqualTo(expected);
    }

    @Property
    void invalidR (@ForAll("invalidRange") int r,
                  @ForAll @IntRange(min = 0, max = 255) int g,
                  @ForAll @IntRange(min = 0, max = 255) int b) {

        assertThrows(IllegalArgumentException.class, () -> Colours.rgbBytesToInt(r, g, b));
    }

    @Property
    void invalidG (@ForAll @IntRange(min = 0, max = 255) int r,
                  @ForAll("invalidRange") int g,
                  @ForAll @IntRange(min = 0, max = 255) int b) {

        assertThrows(IllegalArgumentException.class, () -> Colours.rgbBytesToInt(r, g, b));
    }

    @Property
    void invalidB (@ForAll @IntRange(min = 0, max = 255) int r,
                  @ForAll @IntRange(min = 0, max = 255) int g,
                  @ForAll("invalidRange") int b) {

        assertThrows(IllegalArgumentException.class, () -> Colours.rgbBytesToInt(r, g, b));
    }

    @Property
    void invalidAll (@ForAll("invalidRange") int r,
                    @ForAll("invalidRange") int g,
                    @ForAll("invalidRange") int b) {

        assertThrows(IllegalArgumentException.class, () -> Colours.rgbBytesToInt(r, g, b));
    }

    @Provide
    private Arbitrary<Integer> invalidRange () {
        return Arbitraries.oneOf(
                Arbitraries.integers().lessOrEqual(-1),
                Arbitraries.integers().greaterOrEqual(256)
        );
    }
}