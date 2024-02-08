package assignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() { gameBoard = new GameBoard(); }  // Creating a GameBoard instance before each test

    private boolean isPlayerInsideBoard(int x, int y) { return x >= 0 && x < 6 && y >= 0 && y < 6; }

    @ParameterizedTest
    @MethodSource("generator")
    void testPlayerPosition(String description, int x, int y, boolean expected) {
        int[][] board = gameBoard.getBoard();
        boolean result = isPlayerInsideBoard(x, y);
        Assertions.assertEquals(expected, result, description);
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                // Good Weather Test Cases
                Arguments.of("Inside the board", 2, 3, true),
                Arguments.of("Inside the board", 0, 5, true),
                Arguments.of("Inside the board", 4, 0, true),
                Arguments.of("Inside the board", 1, 1, true),
                Arguments.of("Inside the board", 5, 4, true),

                // Bad Weather Test Cases
                Arguments.of("Outside the board", -1, +3, false),
                Arguments.of("Outside the board", +2, +6, false),
                Arguments.of("Outside the board", +6, +4, false),
                Arguments.of("Outside the board", +7, -2, false),

                // Test cases for the top boundary
                Arguments.of("On the top boundary", 5, 5, true),
                Arguments.of("Off the top boundary", 5, 6, false),

                // Test cases for the left boundary
                Arguments.of("On the left boundary", 0, 3, true),
                Arguments.of("Off the left boundary", -1, 3, false),

                // Test cases for the right boundary
                Arguments.of("On the right boundary", 3, 5, true),
                Arguments.of("Off the right boundary", 6, 5, false),

                // Test cases for the bottom boundary
                Arguments.of("On the bottom boundary", 3, 0, true),
                Arguments.of("Off the bottom boundary", 3, -1, false)
        );
    }
}
