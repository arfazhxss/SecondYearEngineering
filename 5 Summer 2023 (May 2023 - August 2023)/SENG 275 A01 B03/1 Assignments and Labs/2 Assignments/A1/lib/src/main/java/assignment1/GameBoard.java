package assignment1;
import java.util.*;

public class GameBoard {
    private int[][] board;
    public GameBoard() { board = new int[6][6]; } // Creating a 6x6 square board
    public int[][] getBoard() { return board; }
}
