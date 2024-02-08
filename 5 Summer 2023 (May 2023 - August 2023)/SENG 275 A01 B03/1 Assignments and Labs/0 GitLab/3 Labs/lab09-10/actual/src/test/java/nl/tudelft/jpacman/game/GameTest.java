package nl.tudelft.jpacman.game;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for MapParser.
 */
class GameTest {
    private Game game;
    private Player player;
    private Level level;
    private PointCalculator pointCalculator;

    @BeforeEach
    public void setUp() {
        pointCalculator = mock(PointCalculator.class);
        game = new Game(pointCalculator) {
            @Override
            public Level getLevel() {
                return level;
            }

            @Override
            public java.util.List<Player> getPlayers() {
                return null;
            }
        };
        level = mock(Level.class);
        player = mock(Player.class);
        Game gameMock = Mockito.mock(Game.class);
        when(gameMock.isInProgress()).thenReturn(true);
        when(gameMock.getLevel()).thenReturn(level);
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(10);
    }

    @Test
    public void testStartInProgress() {
        game.start();
        assertTrue(game.isInProgress());
        verify(level).addObserver(game);
        verify(level).start();
    }

    @Test
    public void testStartNotInProgress() {
        game.start();
        game.start();
        assertTrue(game.isInProgress());
        verify(level, Mockito.times(1)).addObserver(game);
        verify(level, Mockito.times(1)).start();
    }

    @Test
    public void testStartNoAlivePlayers() {
        when(level.isAnyPlayerAlive()).thenReturn(false);
        game.start();
        assertFalse(game.isInProgress());
        verify(level, Mockito.times(0)).addObserver(game);
        verify(level, Mockito.times(0)).start();
    }

    @Test
    public void testStartNoRemainingPellets() {
        when(level.remainingPellets()).thenReturn(0);
        game.start();
        assertFalse(game.isInProgress());
        verify(level, Mockito.times(0)).addObserver(game);
        verify(level, Mockito.times(0)).start();
    }

    @Test
    public void testStopInProgress() {
        game.start();
        game.stop();
        assertFalse(game.isInProgress());
        verify(level).stop();
    }

    @Test
    public void testStopNotInProgress() {
        game.stop();
        assertFalse(game.isInProgress());
        verify(level, Mockito.times(0)).stop();
    }

    @Test
    public void testLevelWon() {
        game.levelWon();
        assertFalse(game.isInProgress());
        Mockito.verify(level, Mockito.times(0)).stop();
    }

    @Test
    public void testLevelLost() {
        game.levelLost();
        assertFalse(game.isInProgress());
        Mockito.verify(level, Mockito.times(0)).stop();
    }

    @Test
    public void testMoveInProgress() {
        Direction direction = Direction.WEST;
        game.move(player, direction);
        Mockito.verify(level, Mockito.times(0)).move(player, direction);
        Mockito.verify(pointCalculator, Mockito.times(0)).pacmanMoved(player, direction);
    }

    @Test
    public void testMoveNotInProgress() {
        Direction direction = Direction.WEST;
        game.move(player, direction);
        Mockito.verify(level, Mockito.times(0)).move(Mockito.any(Player.class), Mockito.any(Direction.class));
        Mockito.verify(pointCalculator, Mockito.times(0)).pacmanMoved(Mockito.any(Player.class), Mockito.any(Direction.class));
    }

}
