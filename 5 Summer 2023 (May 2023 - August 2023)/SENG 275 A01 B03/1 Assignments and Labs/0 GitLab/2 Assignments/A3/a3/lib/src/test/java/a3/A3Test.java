package a3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import net.jqwik.api.*;
import org.junit.jupiter.api.*;;

class A3Test {
    private StudentDatabaseConnection connection;
    private Registration reg;

    @BeforeEach
    void setup() {
        connection = mock(StudentDatabaseConnection.class);
        reg = new Registration(connection);
    }

    @Test
    void testNoConnection() {
        // Test for correct behavior when the database is not connected.
        when(connection.isConnected()).thenReturn(false);

        // When trying to get the name with any ID, it should throw DatabaseNotConnected exception.
        assertThrows(DatabaseNotConnected.class, () -> reg.name("V12345678"));
    }

    @Test
    void invalidIDs() {
        // Test for correct behavior when the database is connected, but invalidly formatted IDs are submitted.
        when(connection.isConnected()).thenReturn(true);

        // When trying to get the name with an invalid ID, it should throw InvalidIDNumberException.
        assertThrows(InvalidIDNumberException.class, () -> reg.name("123456789")); // No "V" prefix.
        assertThrows(InvalidIDNumberException.class, () -> reg.name("V1234567A")); // Non-digit character in the number.
        assertThrows(InvalidIDNumberException.class, () -> reg.name("V123456")); // Incorrect length (less than 9).
    }

    @Test
    void validIDs() {
        // Test for correct behavior when the database is connected and students in the database are searched for using the correct IDs.
        when(connection.isConnected()).thenReturn(true);

        // When the ID exists in the database, it should return the student's name.
        when(connection.nameFromIDNumber("V12345678")).thenReturn("John Doe");
        assertEquals("John Doe", reg.name("V12345678"));
    }

    @Test
    void notInDatabase() {
        // Test for correct behavior when the database is connected, IDs are provided in the correct format,
        // but there is no corresponding student in the database.
        when(connection.isConnected()).thenReturn(true);

        // When the ID doesn't exist in the database, it should throw StudentNotFoundException.
        when(connection.nameFromIDNumber(anyString())).thenReturn(null);
        assertThrows(StudentNotFoundException.class, () -> reg.name("V99999999"));
    }

    @Property
    void thousandValidNumbers(@ForAll("validIDNumbers") String idNumber) {
        assertTrue(Registration.isValidIDNumber(idNumber));
    }

    @Property
    void thousandInvalidNumbers(@ForAll("invalidIDNumbers") String idNumber) {
        assertFalse(Registration.isValidIDNumber(idNumber));
    }

    @Provide
    Arbitrary<String> validIDNumbers() {
        // Generate 1000 random valid UVic ID numbers
        return Arbitraries.strings()
                .withCharRange('0', '9') // Only digits for the numbers portion
                .ofMinLength(8)
                .ofMaxLength(8)
                .map(numbersPortion -> "V" + numbersPortion);
    }

    @Provide
    Arbitrary<String> invalidIDNumbers() {
        // Generate 1000 random invalid UVic ID numbers with lowercase letter and number from 10000000 to 99999999
        return Arbitraries.strings()
                .withCharRange('a', 'z') // Lowercase letter as prefix
                .flatMap(prefix ->
                        Arbitraries.longs().between(10000000, 99999999) // Random long numbers portion
                                .map(number -> prefix + number)
                );
    }
}

