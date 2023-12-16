package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Room object in the maze.
 *
 * @author Rick Adams.
 * @author Viktoria Dolojan.
 * @author Justin Ho.
 * @version Fall 2023.
 * Trivia Maze - Team 2.
 */
public class Room implements Serializable {
    //======================Constants======================//
    /** Constant for serializable. */
    @Serial
    private static final long serialVersionUID = 2L;

    //=====================Fields==========================//
    /** Map of all the Door objects in the Room. */
    private Map <Direction, Door> myDoors;

    /** The row of the Room in the Maze. */
    private int myRow;
    /** The column of the Room in the Maze. */
    private int myColumn;
    /**
     * Constructs a Room object.
     *
     * @param theRow row of Room in maze
     * @param theColumn column of Room in maze
     */
    public Room(final int theRow, final int theColumn) {
        myDoors = new HashMap<>();
        myRow = theRow;
        myColumn = theColumn;
    }
    /**
     * Get all the Doors in the Room.
     * @return Doors in Room
     */
    public Map<Direction, Door> getAllDoors() {
        return myDoors;
    }

    /**
     * Get the row of the room in the maze.
     * @return int row
     */
    public int getRow() {
        return myRow;
    }

    /**
     * Get the column of the room in the maze.
     * @return int column
     */
    public int getColumn() {
        return myColumn;
    }

    /**
     * Get Door in the specified Direction.
     * @param theDir Direction of Door
     * @return Door in specified Direction
     */
    public Door getDoor(final Direction theDir) {
        return myDoors.get(theDir);
    }

    /**
     * Add a Door to the Room in a Direction.
     * @param theDir Direction of Door in Room
     * @param theDoor Door to be added to Room
     */
    public void addDoor(final Direction theDir, final Door theDoor) {
        myDoors.put(theDir, theDoor);
    }

    /**
     * Overridden toString representation of Room.class.
     *
     * @return Returns raw version of Room.class via String.
     */
    @Override
    public String toString() {
        return "\nRoom: " + myRow + ", " + myColumn;
    }
}
