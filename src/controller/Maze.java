package controller;

import model.Door;
import model.Room;

/**
 * Maze class for Trivia Maze, Team 2.
 * @author Justin Ho
 * @author Viktoria Dolojan
 */

public class Maze {
    /* 2D Room array representing the Trivia Maze. */
    private Room[][] myMaze = null;

    /* 2d Door array representing all the vertical doors in the maze. */
    private Door[][] myVertDoors = null;

    /* 2d Door array representing all the horizontal doors in the maze. */
    private Door[][] myHorzDoors = null;

    /* The entry row of the player. */
    private final int myEntryRow = 0;

    /* The entry column of the player. */
    private final int myEntryCol = 0;

    /* The current row of the player in the maze. */
    private int myCurrentRow = 0;

    /* The current column of the player in the maze. */
    private int myCurrentCol = 0;

    /* Boolean of whether player has reached the exit of the maze. */
    private boolean myGameOver = false;

    /* The size of the maze. */
    private static final int MAZE_SIZE = 6;

    public Maze()  {
        myMaze = new Room[MAZE_SIZE][MAZE_SIZE];

        // fill maze with rooms
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int k = 0; k < MAZE_SIZE; k++) {
                myMaze[i][k] = new Room();
            }
        }
        
        myVertDoors = new Door[MAZE_SIZE - 1][MAZE_SIZE];
        myHorzDoors = new Door[MAZE_SIZE][MAZE_SIZE - 1];
        
        for (int i = 0; i < MAZE_SIZE - 1; i++) {
            for (int k = 0; k < MAZE_SIZE; k++) {
                myVertDoors[i][k] = new Door();
            }
        }

        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int k = 0; k < MAZE_SIZE - 1; k++) {
                myHorzDoors[i][k] = new Door();
            }
        }
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