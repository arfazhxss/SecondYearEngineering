
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

class LeapYearTest {

    @Property
    void LeapYear (@ForAll @IntRange(min = 1) int year) {
        Assumptions.assumeTrue(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        assertThat(LeapYear.isLeapYear(year)).isTrue();
    }

    @Property
    void NonLeapYear (@ForAll @IntRange(min = 1) int year) {
        Assumptions.assumeFalse(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        assertThat(LeapYear.isLeapYear(year)).isFalse();
    }

    @Property
    void InvalidYear (@ForAll("invalidYear") int year) {
        Assumptions.assumeTrue(year < 1);
        assertThrows(IllegalArgumentException.class, () -> LeapYear.isLeapYear(year));
    }

    @Provide
    Arbitrary<Integer> invalidYear() {
        return Arbitraries.integers().lessOrEqual(0);
    }
}