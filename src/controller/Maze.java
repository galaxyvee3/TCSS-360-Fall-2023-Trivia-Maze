package controller;

import model.Door;
import model.Room;

/**
 * Maze class for Trivia Maze, Team 2.
 * @author Justin Ho
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */

public class Maze {
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
     * Moves the player up one row.
     * @return true if player can move up
     */
    public boolean moveUp() {
        if (myCurrentRow - 1 < 0) {
            return false;
        } else {
            myCurrentRow -= 1;
            return true;
        }
    }

    /**
     * Moves the player down one row.
     * @return true if player can move down
     */
    public boolean moveDown() {
        if (myCurrentRow + 1 >= MAZE_SIZE) {
            return false;
        } else {
            myCurrentRow += 1;
            return true;
        }
    }

    /**
     * Moves the player left one column.
     * @return true if player can move left
     */
    public boolean moveLeft() {
        if (myCurrentCol - 1 < 0) {
            return false;
        } else {
            myCurrentCol -= 1;
            return true;
        }
    }

    /**
     * Moves the player right one column.
     * @return true if player can move right
     */
    public boolean moveRight() {
        if (myCurrentCol + 1 >= MAZE_SIZE) {
            return false;
        } else {
            myCurrentCol += 1;
            return true;
        }
    }

    /**
     * Resets all stats for a new game.
     */
    public void newGame() {
        myMaze = new Room[MAZE_SIZE][MAZE_SIZE];
        myVertDoors = new Door[MAZE_SIZE - 1][MAZE_SIZE];
        myHorzDoors = new Door[MAZE_SIZE][MAZE_SIZE - 1];
        createRoomsAndDoors();
        myCurrentRow = 0;
        myCurrentCol = 0;
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
     * Checks the current location of the player in the maze.
     */
    public void checkCurrentLocation() {
        System.out.println("Current Location: " + myCurrentRow + " " + myCurrentCol);
    }

    /**
     * Checks if the player has reached the end of the maze.
     */
    public void checkGameOver() {
        if(myCurrentRow == MAZE_SIZE && myCurrentCol == MAZE_SIZE) {
            myGameOver = true;
        }
    }
}