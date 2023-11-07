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

    public Maze(final String theSize)  {
        int rows = 0;
        int columns = 0;
        if (theSize.equalsIgnoreCase("easy")) {
            rows = 4;
            columns = 4;
        } else if (theSize.equalsIgnoreCase("medium")) {
            rows = 7;
            columns = 7;
        } else if (theSize.equalsIgnoreCase("hard")) {
            rows = 10;
            columns = 10;
        } else {
            throw new IllegalArgumentException("Choose Trivia Maze Difficulty");
        }

        // Default Exit at Bottom Right Corner
        myExitRow = rows - 1;
        myExitCol = columns - 1;
        myMaze = new Room[rows][columns];
        // the total number of doors in the trivia maze
        int numDoors = ((columns - 1) * rows) + ((rows - 1) * columns);
        myDoors = new Door[numDoors];

        // fill maze with rooms
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                myMaze[i][k] = new Room();
            }
        }

        // fill array with doors
        for (int i = 0; i < numDoors; i++) {
            myDoors[i] = new Door(i);
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
     * Return the total number rows in the maze.
     * @return total rows in maze
     */
    public int getRows() {
        return myExitRow + 1;
    }

    /**
     * Return the total number columns in the maze.
     * @return total columns in maze
     */
    public int getCols() {
        return myExitCol + 1;
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