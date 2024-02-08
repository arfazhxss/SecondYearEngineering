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
 * Tests for Inky, Blinky and Clyde Ghosts functioning.
 */
class InkyBlinkyClydeTest {
    private GhostMapParser parser;
    private PacManSprites pacManSprites;

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
     * When there is no Pac-Man on the level.
     */

    @Test
    public void noPacmanTest() {
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
     * When there is no Blinky on the level.
     */

    @Test
    public void noBlinkyTest() {
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
     *  When there is no valid route to PacMan.
     */
    @Test
    public void noRouteTest() {
        //Create map
        List<String> map = Arrays.asList(
                "##########",
                "# I  B  C#",
                "#   ##   #",
                "#   P    #",
                "##########");
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
     *  When the player and Clyde are 8 cells apart, so clyde should be going towards the player.
     */
    @Test
    public void goTowards() {
        //Create map
        List<String> map = Arrays.asList(
                "############",
                "#          #",
                "#P        C#",
                "#          #",
                "############");
        Level level = parser.parseMap(map);

        //Create player on map
        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        //Let ghost make move
        Clyde ghost = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(ghost);
        Optional<Direction> direction = ghost.nextAiMove();

        //Verify
        assertTrue(direction.isPresent());
        assertEquals(Direction.WEST, direction.get());
    }

    /**
     *    When Inky is on the other side of Pac-Man compared to Blinky.
     */
    @Test
    public void inkyOtherSideBlinkyTest() {
        //Create map
        List<String> map = Arrays.asList(
                "##########",
                "#     I  #",
                "#        #",
                "#        #",
                "#        #",
                "#   B P  #",
                "##########");
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
     *  When the player is not on the board, so the ghosts shouldn't do anything.
     */
    @Test
    public void noPacman() {
        //Create map
        List<String> map = Arrays.asList(
                "#######",
                "#  B  #",
                "#  C  #",
                "#  I  #",
                "#######");
        Level level = parser.parseMap(map);
        Clyde ghost = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(ghost);
        Optional<Direction> direction = ghost.nextAiMove();
        assertFalse(direction.isPresent());
    }
    /**
     * When Pac-Man is moving or facing up,
     * the spot Inky uses to draw the line is two squares above
     * and left of Pac-Man.
     */
    @Test
    public void pacmanMovingUpTest() {
        //Create map
        List<String> map = Arrays.asList(
                "#######",
                "#     #",
                "# I   #",
                "#     #",
                "#     #",
                "#     #",
                "#   P #",
                "#   B #",
                "#######");

        Level level = parser.parseMap(map);
        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.NORTH);
        level.registerPlayer(player);

        // Inky makes the next AI move
        Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        assertNotNull(inky);
        Optional<Direction> inkyDirection = inky.nextAiMove();

        // Direction Verification
        assertTrue(inkyDirection.isPresent());
        assertEquals(Direction.NORTH, inkyDirection.get());
    }

    /**
     *  When Inky is alongside Blinky he should be following Blinky.
     */
    @Test
    public void inkyAlongBlinkyTest() {
        //Create map
        List<String> map = Arrays.asList(
                "#######",
                "#  I  #",
                "#     #",
                "#  P  #",
                "#     #",
                "#  B  #",
                "#######");
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
    /**
     * Tests that Clyde tries to get away from Pacman if Clyde is close enough.
     */
    @Test
    public void clydeRunsAwayTest() {
        //Create map
        List<String> map = Arrays.asList(
                "###########",
                "#         #",
                "#  P  C   #",
                "#         #",
                "###########");

        Level level = parser.parseMap(map);
        PlayerFactory playerFactory = new PlayerFactory(pacManSprites);
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.EAST);
        level.registerPlayer(player);

        // Clyde makes the next AI move
        Clyde ghost = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertNotNull(ghost);
        Optional<Direction> direction = ghost.nextAiMove();

        // Verification of Clyde running away
        assertTrue(direction.isPresent());
        assertEquals(Direction.EAST, direction.get());

    }
}