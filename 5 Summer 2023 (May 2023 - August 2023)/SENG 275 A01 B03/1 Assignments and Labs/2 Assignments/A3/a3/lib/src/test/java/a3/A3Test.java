package a3;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.io.IOException;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class A3Test {
    private StudentDatabaseConnection connection;
    private Registration reg;

    @BeforeEach
    void setup() {
        // place code here you'd like to run before each test
    }

    // Implement your tests below.  Feel free to modify the function signatures
    // as necessary - use parameterized testing as you see fit.

    // test for correct behaviour when the database is not connected.

    @Test
    void testNoConnection() {
    }

    // test for correct behaviour when the database is corrected, but invalidly
    //    formatted IDs are submitted.  

    @Test
    void invalidIDs() {
    }

    // test for correct behaviour when the database is connected and students in
    //     the database are searched for using the correct IDs.
    
    @Test
    void validIDs() {
    }

    // test for correct behaviour when the database is connected and IDs are provided but
    // although they are the correct format (correct length, etc), there is no corresponding
    // student in the database.
    
    @Test
    void notInDatabase() {
    }

    // Test whether isValidIDNumber() accepts 1000 properly-formatted ID numbers
    @Property
    void thousandValidNumbers() {
    }

    // Test whether isValidIDNumber() accepts 1000 ID numbers which are correctly formatted
    // except that their first letter is a lowercase letter rather than an uppercase V.
    @Property
    void thousandInvalidLeadingLetters() {
    }
}
