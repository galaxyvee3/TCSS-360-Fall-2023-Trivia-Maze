package controller;

import model.Door;
import model.Room;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Maze class for Trivia Maze, Team 2.
 * @author Justin Ho
 * @author Viktoria Dolojan
 */

public class Maze {

    Door
    /* 2D Room array representing the Trivia Maze. */
    private Room[][] myMaze = null;

    /* 2d Door array representing all the vertical doors in the maze. */
    private Door[][] myVertDoors = null;

    /* 2d Door array representing all the horizontal doors in the maze. */
    private Door[][] myHorzDoors = null;

    /* The entry row of the player. */
    private int myEntryRow = 0;

    /* The entry column of the player. */
    private int myEntryCol = 0;

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

    // TODO: MOVE TO GUI CLASS THAT WILL HANDLE MAZE
    /**
     * Private class that allows the player to traverse the maze using the keyboard.
     * @author Viktoria Dolojan
     * @version Fall 2023.
     */
    private class MovePlayer extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            // WASD and arrow keys
            if (theEvent.getKeyCode() == KeyEvent.VK_W || theEvent.getKeyCode() == KeyEvent.VK_UP) {
                moveUp();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S || theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                moveDown();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_A || theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                moveLeft();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D || theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                moveRight();
            }
        }
    }

    /**
     * Checks if the player can move in the given direction.
     * If there's a locked door, prompts the user with a trivia question.
     * @param rowChange change in row for the player's movement
     * @param colChange change in column for the player's movement
     * @return true if the player can move, false otherwise
     */
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


}