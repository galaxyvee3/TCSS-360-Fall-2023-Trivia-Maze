package tests;

import controller.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    @Test
    void testMazeInit() {
        Maze maze = new Maze("easy");
        assertEquals(24, maze.getDoors().length);
        assertEquals(0, maze.getMyCurrentRow());
        assertEquals(0, maze.getMyCurrentCol());
    }
}