package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Inky Ghost functioning.
 */
class InkyTest {
    private GhostMapParser parser;
    private PacManSprites pacManSprites;

    /**
     * Setting up the GhostMapParser and Sprites to be used by the tests.
     */
    @BeforeEach
    public void prepareGhostMapParser() {
        pacManSprites = new PacManSprites();
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        PointCalculator pointCalculator = new DefaultPointCalculator();
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory, pointCalculator);
        parser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    /**
     * Test when there is no Pac-Man on the level.
     */

    @Test
    public void noPacman() {
        List<String> map1 = Arrays.asList(
                "#######",
                "# I B #",
                "#######"); // Create first map
        Level level1 = parser.parseMap(map1); // Parse first map
        Inky ghost1 = Navigation.findUnitInBoard(Inky.class, level1.getBoard()); // Find Inky ghost on the board
        assertNotNull(ghost1);
        Optional<Direction> direction1 = ghost1.nextAiMove(); // Let ghost make a move
        assertFalse(direction1.isPresent()); // Verify that no direction is available

        List<String> map2 = Arrays.asList(
                "#####",
                "# I #",
                "#   #",
                "#####"); // Create second map

        Level level2 = parser.parseMap(map2); // Parse second map
        Inky ghost2 = Navigation.findUnitInBoard(Inky.class, level2.getBoard()); // Find Inky ghost on the board
        assertNotNull(ghost2);
        Optional<Direction> direction2 = ghost2.nextAiMove(); // Let ghost make a move
        assertFalse(direction2.isPresent()); // Verify that no direction is available
    }

    /**
     * Test when there is no Blinky on the level.
     */

    @Test
    public void noBlinky() {
        List<String> map1 = Arrays.asList(
                "#######",
                "# I P #",
                "#######");
        Level level1 = parser.parseMap(map1);
        Inky ghost1 = Navigation.findUnitInBoard(Inky.class, level1.getBoard());
        assertNotNull(ghost1);
        Optional<Direction> direction1 = ghost1.nextAiMove();
        assertFalse(direction1.isPresent());

        List<String> map2 = Arrays.asList(
                "#####",
                "# I #",
                "# P #",
                "#####");
        Level level2 = parser.parseMap(map2);
        Inky ghost2 = Navigation.findUnitInBoard(Inky.class, level2.getBoard());
        assertNotNull(ghost2);
        Optional<Direction> direction2 = ghost2.nextAiMove();
        assertFalse(direction2.isPresent());
    }

    /**
     * Test when there is no valid route to Pac-Man.
     */
    @Test
    public void notPossibleToDouble() {
        //Create map
        List<String> map = Arrays.asList(
                "#######",
                "# I   #",
                "#     #",
                "#     #",
                "#  P  #",
                "#  B  #",
                "#######");
        Level level = parser.parseMap(map);

        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        Inky ghost = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertNotNull(ghost); // Checking if the ghost (inky) exists
        Optional<Direction> direction = ghost.nextAiMove();
        assertFalse(direction.isPresent()); // checking if the ghost has a direction
    }

    /**
     * Test when Inky is on the other side of Pac-Man compared to Blinky.
     */
    @Test
    public void otherSide() {
        //Create map
        List<String> map = Arrays.asList(
                "#######",
                "# I   #",
                "#     #",
                "#     #",
                "#     #",
                "#BP   #",
                "#######");
        Level level = parser.parseMap(map);

        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.NORTH);
        level.registerPlayer(player);
        Inky ghost = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertNotNull(ghost);
        Optional<Direction> direction = ghost.nextAiMove();
        assertTrue(direction.isPresent());
        assertEquals(Direction.EAST, direction.get());
    }

    /**
     * When Pac-Man is moving or facing up,
     * the spot Inky uses to draw the line is two squares above
     * and left of Pac-Man.
     */
    @Test
    public void inkyBug() {
        //Create map
        List<String> map = Arrays.asList(
                "#####",
                "#   #",
                "#I  #",
                "#   #",
                "#   #",
                "#   #",
                "#  P#",
                "#  B#",
                "#####");
        Level level = parser.parseMap(map);

        //Create player on the Map
        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.NORTH);
        level.registerPlayer(player);

        //Inky makes the move
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertNotNull(inky);
        Optional<Direction> inkyDirection = inky.nextAiMove();

        //Verification
        assertTrue(inkyDirection.isPresent());
        assertEquals(Direction.NORTH, inkyDirection.get());
    }

    /**
     * When Inky is alongside Blinky he should be following Blinky.
     */
    @Test
    public void alongBlinky() {
        //Create map
        List<String> map = Arrays.asList(
                "#####",
                "#I  #",
                "#   #",
                "#P  #",
                "#   #",
                "#B  #",
                "#   #",
                "#   #",
                "#####");
        Level level = parser.parseMap(map);
        //Create player on the Map
        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.SOUTH);
        level.registerPlayer(player);

        //Inky makes the move
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertNotNull(inky);
        Optional<Direction> inkyDirection = inky.nextAiMove();

        //Verification
        assertTrue(inkyDirection.isPresent());
        assertEquals(Direction.SOUTH, inkyDirection.get());
    }
}