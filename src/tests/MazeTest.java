package tests;

import model.Direction;
import model.Door;
import model.Maze;
import model.Room;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeTest {
    @Test
    void testMazeInit() {
        Maze maze = new Maze();
        assertEquals(0, maze.getMyCurrentRow());
        assertEquals(0, maze.getMyCurrentCol());
        assertEquals("Maze init", maze.toString());
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

    @Test
    void testCheckDoorsInRoom00() {
        Maze maze = new Maze();
        Room[][] allRooms = maze.getRooms();
        Room room = allRooms[0][0];
        Room roomRight = allRooms[0][1];
        Room roomBelow = allRooms[1][0];
        HashMap<Direction, Door> expectedValue = new HashMap<Direction, Door>();
        expectedValue.put(Direction.EAST, new Door(room, roomRight, Direction.EAST, Direction.WEST));
        expectedValue.put(Direction.SOUTH, new Door(room, roomBelow, Direction.SOUTH, Direction.NORTH));
        assertEquals(expectedValue, room.getAllDoors());

    }

    @Test
    void testCheckDoorsInRoom01() {
        Maze maze = new Maze();
        Room[][] allRooms = maze.getRooms();
        Room room = allRooms[0][1];
        Room roomLeft = allRooms[0][0];
        Room roomRight = allRooms[0][2];
        Room roomBelow = allRooms[1][0];
        HashMap<Direction, Door> expectedValue = new HashMap<Direction, Door>();
        expectedValue.put(Direction.WEST, new Door(room, roomLeft, Direction.WEST, Direction.EAST));
        expectedValue.put(Direction.EAST, new Door(room, roomRight, Direction.EAST, Direction.WEST));
        expectedValue.put(Direction.SOUTH, new Door(room, roomBelow, Direction.SOUTH, Direction.NORTH));
        assertEquals(expectedValue, room.getAllDoors());
    }

    @Test
    void testCheckDoorsInRoom11() {
        Maze maze = new Maze();
        Room[][] allRooms = maze.getRooms();
        Room room = allRooms[1][1];
        Room roomAbove = allRooms[0][1];
        Room roomBelow = allRooms[2][1];
        Room roomLeft = allRooms[1][0];
        Room roomRight = allRooms[1][2];
        HashMap<Direction, Door> expectedValue = new HashMap<Direction, Door>();
        expectedValue.put(Direction.NORTH, new Door(room, roomAbove, Direction.NORTH, Direction.SOUTH));
        expectedValue.put(Direction.SOUTH, new Door(room, roomBelow, Direction.SOUTH, Direction.NORTH));
        expectedValue.put(Direction.WEST, new Door(room, roomLeft, Direction.WEST, Direction.EAST));
        expectedValue.put(Direction.EAST, new Door(room, roomRight, Direction.EAST, Direction.WEST));
        assertEquals(expectedValue, room.getAllDoors());
    }
}