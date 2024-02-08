package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.anyList;

/**
 * Test class for MapParser.
 */
class MapParserTest {
    private static final int CUSTOM_MAP_GROUND = 9;
    private static final int CUSTOM_MAP_WALLS = 16;
    private static final char[][] TEST_BOARD = new char[][] {
            {'#', '#', '#', '#', '#'},
            {'#', 'P', ' ', ' ', '#'},
            {'#', '.', ' ', ' ', '#'},
            {'#', 'G', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'}};

    /**
     * Tests parsing a valid board with the given valid characters.
     */
    @Test
    public void parseValidBoard() {
        // Mocks
        LevelFactory levelFactory = Mockito.mock(LevelFactory.class);
        BoardFactory boardFactory = Mockito.mock(BoardFactory.class);
        Board fakeBoard = mock(Board.class);
        Level fakeLevel = mock(Level.class);

        when(boardFactory.createBoard(any())).thenReturn(fakeBoard);
        when(levelFactory.createLevel(any(), any(), any())).thenReturn(fakeLevel);

        doReturn(mock(Square.class)).when(boardFactory).createGround();
        doReturn(mock(Square.class)).when(boardFactory).createWall();

        when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
        when(levelFactory.createPellet()).thenReturn(mock(Pellet.class));

        MapParser parser = new MapParser(levelFactory, boardFactory);

        // Parse the map
        Level result = parser.parseMap(TEST_BOARD);

        // Verify board creation
        verify(boardFactory, times(CUSTOM_MAP_GROUND)).createGround();
        verify(boardFactory, times(CUSTOM_MAP_WALLS)).createWall();

        // Verify entity creation
        verify(levelFactory, times(1)).createGhost();
        verify(levelFactory, times(1)).createPellet();

        // Verify behavior
        verify(boardFactory).createBoard(any());
        verify(levelFactory).createLevel(eq(fakeBoard), anyList(), anyList());

        assertSame(fakeLevel, result);
    }

    /**
     * Tests parsing an invalid board due to invalid characters.
     */
    @Test
    public void parseInvalidBoard() {
        // Setup mocks
        LevelFactory levelFactory = Mockito.mock(LevelFactory.class);
        BoardFactory boardFactory = Mockito.mock(BoardFactory.class);
        Board fakeBoard = mock(Board.class);
        Level fakeLevel = mock(Level.class);

        when(boardFactory.createBoard(any())).thenReturn(fakeBoard);
        when(levelFactory.createLevel(any(), any(), any())).thenReturn(fakeLevel);

        doReturn(mock(Square.class)).when(boardFactory).createGround();
        doReturn(mock(Square.class)).when(boardFactory).createWall();

        when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
        when(levelFactory.createPellet()).thenReturn(mock(Pellet.class));

        char[][] testBoard = new char[][] {
                {'#', '#', 'K', '#', ' '},
                {'#', 'P', ' ', ' ', ' '},
                {' ', '.', ' ', ' ', 'K'},
                {'#', 'G', ' ', ' ', ' '},
                {' ', '#', '#', ' ', '#'}
        };
        MapParser parser = new MapParser(levelFactory, boardFactory);

        // Verify exception
        assertThrows(PacmanConfigurationException.class,
                () -> parser.parseMap(testBoard));
    }
}
