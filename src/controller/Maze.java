package controller;

import model.Door;
import model.Room;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Maze class for Trivia Maze, Team 2.
 * @author Viktoria Dolojan
 * @author Justin Ho
 * @version Fall 2023
 * Trivia Maze - Team 2
 */

public class Maze {

    /** Property name for game over. */
    public static final String PROPERTY_GAME_OVER = "Game over";

    /** Property name for updating the maze when player has moved or door has changed status. */
    public static final String PROPERTY_UPDATE_MAZE = "Update maze";

    /** Property name for when the player has moved within the maze. */
    //public static final String PROPERTY_PLAYER_MOVED = "Player moved";

    /** Property name for when a door is unlocked. */
    //public static final String PROPERTY_DOOR_UNLOCKED = "Door unlocked";

    /** Property name for when a door is closed. */
    //public static final String PROPERTY_DOOR_CLOSED = "Door closed";

    private final PropertyChangeSupport myPCS;


    /** 2D Room array representing the Trivia Maze. */
    private Room[][] myMaze = new Room[MAZE_SIZE][MAZE_SIZE];

    /** 2d Door array representing all the vertical doors in the maze. */
    private Door[][] myVertDoors = new Door[MAZE_SIZE - 1][MAZE_SIZE];

    /** 2d Door array representing all the horizontal doors in the maze. */
    private Door[][] myHorzDoors = new Door[MAZE_SIZE][MAZE_SIZE - 1];

    /** The current row of the player in the maze. */
    private int myCurrentRow = 0;

    /** The current column of the player in the maze. */
    private int myCurrentCol = 0;

    /** Boolean of whether player has reached the exit of the maze. */
    private boolean myGameOver = false;

    /** The size of the maze. */
    private static final int MAZE_SIZE = 6;

    /**
     * Default constructor.
     */
    public Maze()  {
        myPCS = new PropertyChangeSupport(this);
        createRoomsAndDoors();
    }

    /**
     * Return the room at the given location.
     * @param theRow row of the room
     * @param theCol column of the room
     * @return the room at the given location
     */
    public Room getRoom(final int theRow, final int theCol) {
        return myMaze[theRow][theCol];
    }

    public Door[][] getVertDoors() {
        return myVertDoors;
    }

    public Door[][] getHorzDoors() {
        return myHorzDoors;
    }

    /**
     * Return the current row.
     * @return current row of the player
     */
    public int getMyCurrentRow() {
        return myCurrentRow;
    }

    /**
     * Return the current column.
     * @return current column of the player
     */
    public int getMyCurrentCol() {
        return myCurrentCol;
    }

    /**
     * Update the current row of the player.
     * @param theRow new current row
     */
    public void setMyCurrentRow(final int theRow) {
        myCurrentRow = theRow;
    }

    /**
     * Update the current column of the player.
     * @param theCol new current column
     */
    public void setMyCurrentCol(final int theCol) {
        myCurrentCol = theCol;
    }

    /**
     * Check if the door is unlocked and player can traverse through.
     * @param theDoor the door being checked
     * @return true if door is unlocked
     */
    public boolean doorUnlocked(final Door theDoor) {
        return theDoor.getUnlocked();
    }

    /**
     * Checks whether the player can move up, down, left, or right.
     * @param theString where to move the player
     * @return true if move was successful
     */
    public boolean canMove(final String theString) {
        if (theString.equalsIgnoreCase("Up")) {
            return getMyCurrentRow() - 1 > 0;
        } else if (theString.equalsIgnoreCase("Down")) {
            return getMyCurrentRow() + 1 < MAZE_SIZE;
        } else if (theString.equalsIgnoreCase("Left")) {
            return getMyCurrentCol() - 1 > 0;
        } else if (theString.equalsIgnoreCase("Right")) {
            return getMyCurrentCol() + 1 < MAZE_SIZE;
        } else {
            return false;
        }
    }

    /**
     * Moves the player up one row.
     * @return if move was successful or why it failed
     */
    public String moveUp() {
        if (canMove("Up")) {
            if (doorUnlocked(myHorzDoors[getMyCurrentRow() - 1][getMyCurrentCol()])) {
                setMyCurrentRow(getMyCurrentRow() - 1);
                return "Moved up.";
            } else {
                return "Door is locked.";
            }
        } else {
            return "Edge of maze.";
        }
    }

    /**
     * Moves the player down one row.
     * @return if move was successful or why it failed
     */
    public String moveDown() {
        if (canMove("Down")) {
            if (doorUnlocked(myHorzDoors[getMyCurrentRow() + 1][getMyCurrentCol()])) {
                setMyCurrentRow(getMyCurrentRow() + 1);
                return "Moved down.";
            } else {
                return "Door is locked.";
            }
        } else {
            return "Edge of maze.";
        }
    }

    /**
     * Moves the player left one column.
     * @return if move was successful or why it failed
     */
    public String moveLeft() {
        if (canMove("Left")) {
            if (doorUnlocked(myHorzDoors[getMyCurrentRow()][getMyCurrentCol() - 1])) {
                setMyCurrentCol(getMyCurrentCol() - 1);
                return "Moved left.";
            } else {
                return "Door is locked.";
            }
        } else {
            return "Edge of maze.";
        }
    }

    /**
     * Moves the player right one column.
     * @return if move was successful or why it failed
     */
    public String moveRight() {
        if (canMove("Right")) {
            if (doorUnlocked(myHorzDoors[getMyCurrentRow()][getMyCurrentCol() + 1])) {
                setMyCurrentCol(getMyCurrentCol() + 1);
                return "Moved right.";
            } else {
                return "Door is locked.";
            }
        } else {
            return "Edge of maze.";
        }
    }

    /**
     * Fill arrays with rooms and doors in the maze.
     */
    public void createRoomsAndDoors() {
        // fill maze with rooms
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int k = 0; k < MAZE_SIZE; k++) {
                myMaze[i][k] = new Room();
            }
        }

        // fill array with vertical doors
        for (int i = 0; i < MAZE_SIZE - 1; i++) {
            for (int k = 0; k < MAZE_SIZE; k++) {
                myVertDoors[i][k] = new Door();
            }
        }

        // fill array with horizontal doors
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int k = 0; k < MAZE_SIZE - 1; k++) {
                myHorzDoors[i][k] = new Door();
            }
        }
    }

    /**
     * Resets all stats for a new game.
     */
    public void newGame() {
        // save old values for firePropertyChange
        final Room[][] maze = myMaze;
        final boolean oldGameOver = myGameOver;


        // replace old values with new values
        myMaze = new Room[MAZE_SIZE][MAZE_SIZE];
        myVertDoors = new Door[MAZE_SIZE - 1][MAZE_SIZE];
        myHorzDoors = new Door[MAZE_SIZE][MAZE_SIZE - 1];
        createRoomsAndDoors();
        myCurrentRow = 0;
        myCurrentCol = 0;
        myGameOver = false;


        // firePropertyChange
        myPCS.firePropertyChange(PROPERTY_GAME_OVER, oldGameOver, myGameOver);
    }

    /**
     * Checks the current location of the player in the maze.
     */
    public void checkCurrentLocation() {
        System.out.println("Current Location: " + myCurrentRow + " " + myCurrentCol);
    }

    /**
     * Checks if the player has reached the end of the maze.
     */
    public void gameOverSuccess() {
        if(myCurrentRow == (MAZE_SIZE - 1) && myCurrentCol == (MAZE_SIZE - 1)) {
            final boolean oldGameOver = myGameOver;
            myGameOver = true;
            myPCS.firePropertyChange(PROPERTY_GAME_OVER, oldGameOver, myGameOver);
        }
    }

    /**
     * Checks if player is trapped in the maze because all possible doors are locked.
     */
    public void gameOverFail() {

    }

    /**
     * Adds a PropertyChangeListener to PropertyChangeSupport.
     * @param theListener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    /**
     * Removes a PropertyChangeListener from PropertyChangeSupport.
     * @param theListener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }

    @Override
    public String toString() {
        return "Maze init";
    }


    /**
     * Checks if the player can move in the given direction.
     * If there's a locked door, prompts the user with a trivia question.
     * @param rowChange change in row for the player's movement
     * @param colChange change in column for the player's movement
     * @return true if the player can move, false otherwise
     */
    /*
    public boolean canMove (int rowChange, int colChange) {
        int newRow = myCurrentRow + rowChange;
        int newCol = myCurrentCol + colChange;

        // Check if new player position within the bounds
        if (newRow >= MAZE_SIZE || newRow < 0 || newCol >= MAZE_SIZE || newCol < 0) {
            return false;
        }

        // Get new room and new door for player
        Room nextRoom = myMaze[newRow][newCol];
        Door door = (rowChange == 0) ?
                myHorzDoors[newRow][Math.min(newCol, myCurrentRow)]:
                myVertDoors[Math.min(newRow, myCurrentRow)][newCol];

        if (Door)

        String question = door.getQuestion();
        String answer = door.getAnswer();



        return false;

    }
*/

}