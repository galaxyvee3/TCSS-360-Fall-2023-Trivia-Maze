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

    /* Array of Doors in the Trivia Maze. */
    private Door[] myDoors = null;

    /* The entry row of the player. */
    private int myEntryRow = 0;

    /* The entry column of the player. */
    private int myEntryCol = 0;

    /* The current row of the player in the maze. */
    private int myCurrentRow = 0;

    /* The current column of the player in the maze. */
    private int myCurrentCol = 0;

    /* The exit row for the maze. */
    private int myExitRow;

    /* The exit column for the maze. */
    private int myExitCol;

    /* Boolean of whether player has reached the exit of the maze. */
    private boolean myGameOver = false;

    public Maze(final int theRows, int theColumns)  {
        if (theRows < 1 || theColumns  < 1) {
            throw new IndexOutOfBoundsException("Maze cannot be smaller than a 2x2.");
        }

        // Default Exit at Bottom Right Corner
        myExitRow = theRows - 1;
        myExitCol = theColumns - 1;
        myMaze = new Room[theRows][theColumns];
        // the total number of doors in the trivia maze
        int numDoors = ((theColumns - 1) * theRows) + ((theRows - 1) * theColumns);
        myDoors = new Door[numDoors];

        // fill maze with rooms
        for (int i = 0; i < theRows; i++) {
            for (int k = 0; k < theColumns; k++) {
                myMaze[i][k] = new Room();
            }
        }

        // fill array with doors
        for (int i = 0; i < numDoors; i++) {
            myDoors[i] = new Door();
        }
    }

    /**
     * Return array of doors.
     * @return all the doors in the trivia maze
     */
    public Door[] getDoors() {
        return myDoors;
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
     * FOR TESTING PURPOSES
     * Checks the current location of the player in the maze.
     */
    public void checkCurrentLocation() {
        System.out.println("Current Location: " + myCurrentRow + " " + myCurrentCol);
    }

    /**
     * Checks if the player has reached the end of the maze.
     */
    public void checkGameOver() {
        if(myCurrentRow == myExitRow && myCurrentCol == myExitCol) {
            myGameOver = true;
        }
    }
}