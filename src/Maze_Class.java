import Model.Direction;
import Model.Door;
import Model.Room;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Maze class for Trivia Maze, Team 2.
 * @author Justin Ho
 * @author Viktoria Dolojan
 */

public class Maze_Class {
    /* 2D Room array representing the Trivia Maze. */
    private Room[][] myMaze;

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

    public Maze_Class (final int theRows, int theColumns)  {
        // Default Exit at Bottom Right Corner
        myExitRow = theRows - 1;
        myExitCol = theColumns - 1;
        myMaze = new Room[theRows][theColumns];

        // TODO: fill maze with rooms
        // first room
        myMaze[0][0] = new Room(0, 0);

        for (int i = 1; i < theRows - 1; i++) {
            for (int k = 1; k < theColumns - 1; k++) {
                myMaze[i][k] = new Room();
            }
        }
    }

    /**
     * FOR TESTING PURPOSES
     * Checks the current location of the player in the maze.
     */
    public void checkCurrentLocation() {
        System.out.println(myCurrentRow + " " + myCurrentCol);
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