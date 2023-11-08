package tests;

import controller.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    @Test
    void testMazeInit() {
        Maze maze = new Maze();
        assertEquals(0, maze.getMyCurrentRow());
        assertEquals(0, maze.getMyCurrentCol());
    }

    @Test
    void testMazeUpdateLocation() {
        Maze maze = new Maze();
        assertEquals(0, maze.getMyCurrentRow());
        assertEquals(0, maze.getMyCurrentCol());
        maze.setMyCurrentRow(1);
        maze.setMyCurrentCol(1);
        assertEquals(1, maze.getMyCurrentRow());
        assertEquals(1, maze.getMyCurrentCol());
    }
}