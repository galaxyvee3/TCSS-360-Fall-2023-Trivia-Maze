package tests;

import controller.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    @Test
    void testMazeInit() {
        Maze maze = new Maze(2,2);
        assertEquals(4, maze.getDoors().length);
        assertEquals(0, maze.getMyCurrentRow());
        assertEquals(0, maze.getMyCurrentCol());
    }
}