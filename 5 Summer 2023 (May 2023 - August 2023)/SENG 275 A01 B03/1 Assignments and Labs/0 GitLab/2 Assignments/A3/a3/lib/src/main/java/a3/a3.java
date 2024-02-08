package a3;
import java.io.IOException;

class StudentNotFoundException extends RuntimeException {}
class InvalidIDNumberException extends RuntimeException {}
class DatabaseNotConnected extends RuntimeException {}


interface StudentDatabaseConnection {
    /*
    nameFromIDNumber()

    Given an ID number (encoded as a string), will return the name
    of the corresponding student in the database.  If the ID number
    does not correspond to any student in the database, a StudentNotFound
    exception is raised.
     */
    public String nameFromIDNumber(String idNumber) throws StudentNotFoundException;

    /*
    isConnected()

    Returns the connection status of the database.  It is undefined behaviour
    to call nameFromIDNumber() when the connection is not present.
     */
    public boolean isConnected();
}

class Registration {

    StudentDatabaseConnection database;

    public Registration(StudentDatabaseConnection connection) {
        database = connection;
    }

    /*
    isValidIDNumber()

    Valid UVic ID numbers begin with an uppercase letter V, and are
    followed with a sequence of eight digits.  This function returns
    true if the string reference contains a valid ID and nothing else,
    and false otherwise.
     */

    public static boolean isValidIDNumber (String idNumber) {
        if (idNumber == null) return false;
        if (idNumber.length() != 9) return false;
        if (!idNumber.startsWith("V")) return false;
        String numbersPortion = idNumber.substring(1, 9);
        int value;
        try { value = Integer.parseInt(numbersPortion); }
        catch (NumberFormatException e) { return false; }
        return value >= 0;
    }

    /*
    name()

    Given a student name in a string "Firstname Lastname", this
    function consults the StudentDatabaseConnection database, and
    returns the student ID provided by that database.

    If the argument is an invalid student number, an InvalidIDNumberException
    is thrown.

    Precondition: If the database is not connected, a DatabaseNotConnected
    exception is thrown.

    Postcondition:  This function will never return null to the caller.
     */

    public String name(String idNumber) {
        // Precondition: If the database is not connected, a DatabaseNotConnected exception is thrown.
        if (!database.isConnected()) { throw new DatabaseNotConnected(); }

        // Precondition design-by-contract code here
        // idNumber should be a valid UVic ID number
        if (!isValidIDNumber(idNumber)) { throw new InvalidIDNumberException(); }

        // Main logic to fetch the student name from the database.
        String studentName = database.nameFromIDNumber(idNumber);

        // Post-Condition design-by-contract code here
        // This function will never return null to the caller
        if (studentName == null) { throw new StudentNotFoundException(); }

        return studentName;
    }
}

