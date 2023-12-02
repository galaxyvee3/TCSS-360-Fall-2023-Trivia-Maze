package model;

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

    /** Property name for updating the maze when player has moved or status of doors has changed. */
    public static final String PROPERTY_UPDATE_MAZE = "Update maze";

    /**  Property name for when there is a trivia question. */
    public static final String PROPERTY_TRIVIA_QUESTION = "Trivia question";

    /** The size of the maze. */
    private static final int MAZE_SIZE = 6;

    /** Property change support for the class. */
    private final PropertyChangeSupport myPCS;

    /** 2D Room array representing the Trivia Maze. */
    private Room[][] myMaze;

    /** The current row of the player in the maze. */
    private int myCurrentRow;

    /** The current column of the player in the maze. */
    private int myCurrentCol;

    /** Boolean of whether player has reached the exit of the maze. */
    private boolean myGameOver;

    /** The current trivia question from the current door. */
    private String myQuestion;

    /**
     * Default constructor.
     */
    public Maze() {
        myMaze = new Room[MAZE_SIZE][MAZE_SIZE];
        myCurrentRow = 0;
        myCurrentCol = 0;
        myGameOver = false;
        myQuestion = null;
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

    /**
     * Fill arrays with rooms and doors in the maze.
     */
    private void createRoomsAndDoors() {
        // fill maze with rooms
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int k = 0; k < MAZE_SIZE; k++) {
                myMaze[i][k] = new Room();
            }
        }

        // fill rooms with doors
        // horizontal doors
        for (int rows = 0; rows < MAZE_SIZE - 1; rows++) {
            for (int index = 0; index < MAZE_SIZE; index++) {
                Room room1 = getRoom(rows, index); // room above
                Room room2 = getRoom(rows + 1, index); // room below
                Door door = new Door(room1, room2, Direction.SOUTH, Direction.NORTH);
            }
        }
        // vertical doors
        for (int index = 0; index < MAZE_SIZE; index++) {
            for (int cols = 0; cols < MAZE_SIZE - 1; cols++) {
                Room room1 = getRoom(index, cols); // left room
                Room room2 = getRoom(index, cols + 1); // right room
                Door door = new Door(room1, room2, Direction.EAST, Direction.WEST);
            }
        }
    }

    /**
     * Get all the rooms in the maze.
     * @return 2d Room array of maze
     */
    public Room[][] getRooms() {
        return myMaze;
    }

    /**
     * Check whether the game is over or not.
     * @return true if player has escaped the maze
     */
    public boolean getGameOver() {
        return myGameOver;
    }

    /**
     * Check the current row.
     * @return current row of the player
     */
    public int getMyCurrentRow() {
        return myCurrentRow;
    }

    /**
     * Check the current column.
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
        if (theDoor.getUnlocked()) {
            myPCS.firePropertyChange(PROPERTY_UPDATE_MAZE, false, true);
        }
        return theDoor.getUnlocked();
    }

    /**
     * Check if the door is closed and player cannot traverse through.
     * @param theDoor the door being checked
     * @return true if door is closed
     */
    public boolean doorClosed(final Door theDoor) {
        if (theDoor.getClosed()) {
            myPCS.firePropertyChange(PROPERTY_UPDATE_MAZE, false, true);
        }
        return theDoor.getClosed();
    }

    /**
     * Checks whether the player can move up, down, left, or right.
     * @param theString where to move the player
     * @return true if move was successful
     */
    private boolean canMove(final String theString) {
        if (theString.equalsIgnoreCase("Up")) {
            return (getMyCurrentRow() - 1) >= 0;
        } else if (theString.equalsIgnoreCase("Down")) {
            return (getMyCurrentRow() + 1) < MAZE_SIZE;
        } else if (theString.equalsIgnoreCase("Left")) {
            return (getMyCurrentCol() - 1) >= 0;
        } else if (theString.equalsIgnoreCase("Right")) {
            return (getMyCurrentCol() + 1) < MAZE_SIZE;
        } else {
            return false;
        }
    }

    /**
     * Prompt the trivia question to attempt to unlock door.
     * @param theDoor door that is being attempted
     */
    private void promptQuestion(final Door theDoor) {
        // player has encountered a locked door
        // prompt trivia question from door
        String oldQuestion = myQuestion;
        System.out.println(theDoor.getQuestion());
        myQuestion = theDoor.getQuestion();
        //myPCS.firePropertyChange(PROPERTY_TRIVIA_QUESTION, oldQuestion, myQuestion);

        // TODO: prompt question and validate player answer

        // unlock or close door based on player answer
        /*if () { // player answered correctly, unlock door
            theDoor.unlockDoor();
        } else { // player answered incorrectly, close door
            theDoor.closeDoor();
        }*/
        //myPCS.firePropertyChange(PROPERTY_UPDATE_MAZE, true, true);
    }

    /**
     * Moves the player up one row.
     * @return if move was successful or why it failed
     */
    public String moveUp() {
        if (canMove("Up")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.NORTH);
            if (doorUnlocked(door)) { // unlocked door, move up
                setMyCurrentRow(getMyCurrentRow() - 1);
                gameOverSuccess(); // check whether player has escaped the maze
                return "Moved up." + getMyCurrentRow();
            } else if (doorClosed(door)) { // closed door, dont move
                return "Door is closed.";
            } else { // locked door, prompt question
                promptQuestion(door);
                return "Door is locked.";
            }
        } else { // prevent player from leaving maze
            setMyCurrentRow(getMyCurrentRow());
            return "Edge of maze.";
        }
    }

    /**
     * Moves the player down one row.
     * @return if move was successful or why it failed
     */
    public String moveDown() {
        if (canMove("Down")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.SOUTH);
            if (doorUnlocked(door)) { // unlocked door, move down
                setMyCurrentRow(getMyCurrentRow() + 1);
                gameOverSuccess(); // check whether player has escaped the maze
                return "Moved down." + getMyCurrentRow();
            } else if (doorClosed(door)) { // closed door, dont move
                return "Door is closed.";
            } else { // locked door, prompt question
                promptQuestion(door);
                return "Door is locked.";
            }
        } else { // prevent player from leaving maze
            setMyCurrentRow(getMyCurrentRow());
            return "Edge of maze.";
        }
    }

    /**
     * Moves the player left one column.
     * @return if move was successful or why it failed
     */
    public String moveLeft() {
        if (canMove("Left")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.WEST);
            if (doorUnlocked(door)) { // unlocked door, move left
                setMyCurrentCol(getMyCurrentCol() - 1);
                gameOverSuccess(); // check whether player has escaped the maze
                return "Moved left." + getMyCurrentCol();
            } else if (doorClosed(door)) { // closed door, dont move
                return "Door is closed.";
            } else { // locked door, prompt question
                promptQuestion(door);
                return "Door is locked.";
            }
        } else { // prevent player from leaving maze
            setMyCurrentCol(getMyCurrentCol());
            return "Edge of maze.";
        }
    }

    /**
     * Moves the player right one column.
     * @return if move was successful or why it failed
     */
    public String moveRight() {
        if (canMove("Right")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.EAST);
            if (doorUnlocked(door)) { // unlocked door, move right
                setMyCurrentCol(getMyCurrentCol() + 1);
                gameOverSuccess(); // check whether player has escaped the maze
                return "Moved right." + getMyCurrentCol();
            } else if (doorClosed(door)) { // closed door, dont move
                return "Door is closed.";
            } else { // locked door, prompt question
                promptQuestion(door);
                return "Door is locked.";
            }
        } else { // prevent player from leaving maze
            setMyCurrentCol(getMyCurrentCol());
            return "Edge of maze.";
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
        createRoomsAndDoors();
        myCurrentRow = 0;
        myCurrentCol = 0;
        myGameOver = false;


        // game over
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
}