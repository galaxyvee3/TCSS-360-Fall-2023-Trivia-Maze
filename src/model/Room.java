package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Room object in the maze.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @author Justin Ho
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class Room implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

//======================Constants======================//
    private static final Logger LOGGER = Logger.getLogger(Room.class.getName());

    private static final Random RANDOM = new Random();

    private static final int EDGE_COUNT = 2;

    private static final int INNER_COUNT = 4;

//=====================Fields==========================//
    /** Map of all the Door objects in the Room. */
    private HashMap<Direction, Door> myDoors;

    /** Keeps track of whether the room has been visited. */
    private boolean myVisited;

    /** The row of the Room in the Maze. */
    private int myRow;

    private  int myRowCnt;

    /** The column of the Room in the Maze. */
    private int myColumn;

    private int myColCnt;

    /** Boolean for whether there is a clue available. */
    private boolean myClueStatus;

    /** The clue for the trivia question. */
    private String myClueContent;

    /** The clue manager for the Room. */
    private ClueManager myCM;

    /**
     * Default constructor.
     */
    public Room(final int theRow, final int theColumn) {
        myDoors = new HashMap<>();
        myVisited = false;
        myRow = theRow;
        myColumn = theColumn;
        myCM = new ClueManager();
        initializeState();
        initializeClue();
        generateClueContent();
    }

    /**
     * Checks whether room has been visited or not.
     * @return true if room has been visited
     */
    public boolean getVisited() {
        return myVisited;
    }

    /**
     * Sets visited to true to prevent infinite recursion for maze traversal algorithm.
     */
    public void setVisited() {
        myVisited = true;
    }

    /**
     * Get all the Doors in the Room.
     * @return Doors in Room
     */
    public HashMap<Direction, Door> getAllDoors() {
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
     * Constructs a Room object.
     * @param theRow row of Room in maze
     * @param theColumn column of Room in maze
     */
    public Room(final int theRow, final int theColumn,
                final int theRowCnt, final int theColCnt) {
        initializePosition(theRow, theColumn, theRowCnt, theColCnt);
    }

    /**
     * Returns whether a clue is available.
     * @return true if there is a clue available
     */
    public boolean isCluePresent() {
        return myClueStatus;
    }

    /**
     * Returns the clue to help the user answer the trivia question.
     * @return clue
     */
    public String getMyClueContent() {
        return myClueContent;
    }

    /**
     * Generates a clue for the trivia question.
     * @return clue
     */
    private String generateClueContent() {
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = myCM.getClues();
        } else {
            myClueContent = "No bonus item present.";
        }
        return myClueContent;
    }

    private boolean isOnEdgeOfMaze() {
        // Check if the room is on the edge of the maze
        return myRow == 0 || myRow == myRowCnt - 1
                || myColumn == 0 || myColumn == myColCnt - 1;
    }

    private int doorCounter() {
        final int doorCount;
        if (isOnEdgeOfMaze()) {
            doorCount = EDGE_COUNT;
        } else {
            doorCount = INNER_COUNT;
        }
        return doorCount;
    }

    public int getDoorCount() {
        return doorCounter();
    }
    private void initializePosition(final int theRow, final int theColumn,
                                    final int theRowCnt, final int theColCnt) {
        myRow = theRow;
        myColumn = theColumn;
        myRowCnt = theRowCnt;
        myColCnt = theColCnt;
    }

    private void initializeState() {
    }

    private void initializeClue() {
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = generateClueContent();
        }
    }

    private void logDoorCount() {
        final int doorCount = doorCounter();
        LOGGER.info("Door Count: " + doorCount);
    }

    @Override
    public String toString() {
        return "\nRoom: " + myRow + ", " + myColumn;
    }

}