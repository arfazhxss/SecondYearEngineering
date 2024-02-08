package lab03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class SpecificationTest {
    @ParameterizedTest(name="expectedCondition{0},x{1},y{2}")
    @CsvSource({"true,0,0","true,1280,720","false,-1,-1","false,1281,721"})
    void HighDefinitionTest (boolean expectedCondition, int x, int y) {
        Specification.setDefinition(0);
        if (expectedCondition) {
            assertTrue(Specification.insideDisplayArea(x,y));
        } else {
            assertFalse(Specification.insideDisplayArea(x,y));
        }
    }
    @ParameterizedTest(name="expectedCondition{0},x{1},y{2}")
    @CsvSource({"true,0,0","true,1280,720","true,1920,1080","false,-1,-1","false,1921,1081"})
    void FullHighDefinitionTest (boolean expectedCondition, int x, int y) {
        Specification.setDefinition(1);
        if (expectedCondition) {
            assertTrue(Specification.insideDisplayArea(x,y));
        } else {
            assertFalse(Specification.insideDisplayArea(x,y));
        }
    }

    /** Evaluates a personalized BC license plate message for validity according to some rules
     * at https://www.icbc.com/vehicle-registration/licence-plates/Pages/Personalized-licence-plates.aspx
     *
     * Specifically the message must:
     *  - have 2-6 letters or letters and numbers
     *  - can include blank spaces or hyphens (each such space or hyphen must have a letter or number
     *      both before it and after it).
     *  - can be a maximum of 7 characters if it contains a hyphen (a maximum of 6 characters if the
     *      plate is for a motorcycle).
     *  - must not be composed only of numbers
     *
     *  Only the rules above are used for this exercise; any other rules on the icbc website other than the ones
     *  above may be disregarded.
     *
     * @param input     The intended message
     * @param motorcycle True for a motorcycle, false otherwise
     * @return          True if the message is valid according to these rules
     */
    @ParameterizedTest(name="vehicleType{0},shouldReturn{1},input{2}")
    @CsvSource({
            "motor, true, A5meD1",
            "motor, true, A 1",
            "motor, true, A7MD1",
            "motor, true, A55 d1",
            "motor, true, Ahm-811",
            "motor, true, A m-811",
            "motor, true, A9R 1BR",
            "motor, true, AbC",
            "motor, true, Ab",
            "motor, true, A-BC123",
            "motor, false, 12345",
            "motor, false, 123456",
            "motor, false, A1B2C3D",
            "motor, true, A123BC",
            "motor, false, A-B-C-1-2-3",
            "car, false, AHmeD1",
            "car, false, AHMED1",
            "other, false, AHMD1",
            "car, false, AHmd1",
            "test, false, AHM-ED1",
            "car, false, ABC12",
            "car, false, 12345",
            "train, false, 123456",
            "car, false, A1B2C3D",
            "car, false, AbC",
            "car, false, A123BC",
            "car, false, A-BC123",
            "car, false, A-B-C-1-2-3"
    })
    void validLicensePlate (String vehicleType, boolean shouldReturn, String input) {
        if (vehicleType.equals("motor")) { assertEquals(shouldReturn, Specification.messageIsValid(input, true)); }
        else assertEquals(shouldReturn, Specification.messageIsValid(input, false));
    }

    @ParameterizedTest(name="vehicleType{0},shouldReturn{1},input{2}")
    @CsvSource({
            "motor, true, A5meD1",
            "motor, true, A 1",
            "motor, true, A7MD1",
            "motor, true, A55 d1",
            "motor, true, Ahm-811",
            "motor, true, A m-811",
            "motor, true, A9R 1BR",
            "motor, true, AbC",
            "motor, true, Ab",
            "motor, true, A-BC123",
            "motor, false, 12345",
            "motor, false, 123456",
            "motor, false, A1B2C3D",
            "motor, true, A123BC",
            "motor, false, A-B-C-1-2-3",
            "car, false, AHmeD1",
            "car, false, AHMED1",
            "other, false, AHMD1",
            "car, false, AHmd1",
            "test, false, AHM-ED1",
            "car, false, ABC12",
            "car, false, 12345",
            "train, false, 123456",
            "car, false, A1B2C3D",
            "car, false, AbC",
            "car, false, A123BC",
            "car, false, A-BC123",
            "car, false, A-B-C-1-2-3"
    })
    void validLicensePlate2 (String vehicleType, boolean shouldReturn, String input) {
        if (vehicleType.equals("motor")) { assertEquals(shouldReturn, Specification.messageIsValidCorrected(input, true)); }
        else assertEquals(shouldReturn, Specification.messageIsValidCorrected(input, false));
    }

}