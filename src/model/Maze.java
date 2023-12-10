package model;

import view.Question;
import view.QuestionAnswer;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Maze class for Trivia Maze, Team 2.
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */

public class Maze implements Serializable {
//======================Constants======================//
    /** Property name for game over. */
    transient public static final String PROPERTY_GAME_OVER = "Game over";

    /** Property name for updating the maze when player has moved or status of doors has changed. */
    transient public static final String PROPERTY_UPDATE_MAZE = "Update maze";

    /**  Property name for when there is a trivia question. */
    transient public static final String PROPERTY_TRIVIA_QUESTION = "Trivia question";

    /** Property name for when the player has saved the games current state. */
    transient private static final String PROPERTY_SAVE = "GAME SAVED";

    /** Property name for when the player returns to the last saved game state. */
    transient private static final String PROPERTY_PREV = "PREV GAME STATE";

    /** Property name for when the player has reached the exit and has won the game.*/
    transient private static final String PROPERTY_WIN = "PLAYER WINS";

    /** The size of the maze. */
    transient private static final int MAZE_SIZE = 6;

//=====================Fields==========================//
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

    /** Boolean for whether player is currently attempting a door and should not move. */
    private boolean myAttemptDoor;

    /** The current Door being attempted. */
    private Door myCurrentDoor;

    /** The current trivia question from the current Door. */
    private Question myQuestion;

    /**
     * Default constructor.
     */
    public Maze() {
        myPCS = new PropertyChangeSupport(this);
        myMaze = new Room[MAZE_SIZE][MAZE_SIZE];
        myCurrentRow = 0;
        myCurrentCol = 0;
        myGameOver = false;
        myAttemptDoor = false;
        myCurrentDoor = null;
        myQuestion = new Question("", "");
        createRoomsAndDoors();
    }

    /**
     * Get the room at the given location.
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

        // attach trivia question to doors
        QuestionAnswer qa = new QuestionAnswer();
        ArrayList<Question> questions = qa.getQuestions();
        int i = 0;

        Random random = new Random();
        // TODO: NEED MORE QUESTIONS FROM DATABASE
        // fill rooms with doors
        // horizontal doors
        for (int rows = 0; rows < MAZE_SIZE - 1; rows++) {
            for (int index = 0; index < MAZE_SIZE; index++) {
                Room room1 = getRoom(rows, index); // room above
                Room room2 = getRoom(rows + 1, index); // room below
                Door door = new Door(room1, room2, Direction.SOUTH, Direction.NORTH);
                if (i < questions.size()) {
                    if (questions.get(i) != null) { // attach question to door
                        door.setQuestion(questions.get(i));
                    }
                }
                i++;
                if (index == 1 || rows == 0 || index == MAZE_SIZE - 1) {
                    // these doors will have default status to prevent assure a playable path
                } else { // randomly set the status of doors
                    if (random.nextInt(101) < 20) {
                        door.unlockDoor();
                    } else {
                        if (random.nextInt(101) < 70) {
                            door.closeDoor();
                        }
                    }
                }
            }
        }
        // vertical doors
        for (int index = 0; index < MAZE_SIZE; index++) {
            for (int cols = 0; cols < MAZE_SIZE - 1; cols++) {
                Room room1 = getRoom(index, cols); // left room
                Room room2 = getRoom(index, cols + 1); // right room
                Door door = new Door(room1, room2, Direction.EAST, Direction.WEST);
                if (i < questions.size()) {
                    if (questions.get(i) != null) { // attach question to door
                        door.setQuestion(questions.get(i));
                    }
                }
                i++;
                if (index == 0 || cols == 1 || index == MAZE_SIZE - 2) {
                    // these doors will have default status to prevent assure a playable path
                } else { // randomly set the status of doors
                    if (random.nextInt(101) < 20) {
                        door.unlockDoor();
                    } else {
                        if (random.nextInt(101) < 70) {
                            door.closeDoor();
                        }
                    }
                }
            }
        }

        Door door = getRoom(0,0).getDoor(Direction.EAST);
        door.setQuestion(questions.get(0));
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
     * CHEAT: unlock door with N key
     */
    public void unlockDoor() {
        myAttemptDoor = false;
        myCurrentDoor.unlockDoor();
        myPCS.firePropertyChange(PROPERTY_UPDATE_MAZE, false, true);
    }

    /**
     * CHEAT: close door with M key
     */
    public void closeDoor() {
        myAttemptDoor = false;
        myCurrentDoor.closeDoor();
        myPCS.firePropertyChange(PROPERTY_UPDATE_MAZE, false, true);
    }

    /**
     * Prompt the trivia question to attempt to unlock door.
     * @param theDoor door that is being attempted
     */
    private void promptQuestion(final Door theDoor) {
        // player has encountered a locked door
        myCurrentDoor = theDoor;
        myAttemptDoor = true;
        // prompt trivia question from door
        myQuestion = theDoor.getQuestion();
        myPCS.firePropertyChange(PROPERTY_TRIVIA_QUESTION, null, myQuestion);

        String message = "Please enter ";
        if (myQuestion.getQuestionType().equalsIgnoreCase("MULTIPLE_CHOICE")) {
            message += "A, B, or C";
        } else if (myQuestion.getQuestionType().equalsIgnoreCase("TRUE_FALSE")) {
            message += "true or false";
        } else if (myQuestion.getQuestionType().equalsIgnoreCase("SHORT_ANSWER")) {
            message += "one word";
        }

        // prompt player to answer
        String playerAnswer = JOptionPane.showInputDialog(null, message);
        // check if player answer is correct
        if (playerAnswer.equalsIgnoreCase("unlock")) {
            unlockDoor(); // CHEAT TO UNLOCK DOOR
        } else if (playerAnswer.equalsIgnoreCase("close")) {
            closeDoor(); // CHEAT TO CLOSE DOOR
        } else if (myQuestion.getAnswer().equalsIgnoreCase(playerAnswer)) { // unlock door bc player is correct
            unlockDoor();
            JOptionPane.showMessageDialog(null, "Correct! The door is unlocked.");
        } else { // close door bc player is incorrect
            closeDoor();
            JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is: " + myQuestion.getAnswer() + "\nThe door is closed.");
        }
    }

    /**
     * Checks whether the player can move up, down, left, or right.
     * @param theString where to move the player
     * @return true if move was successful
     */
    private boolean canMove(final String theString) {
        if (myAttemptDoor) { // player is currently attempting a door and should not move
            System.out.println("Currently attempting a door");
            return false;
        } else { // player is not attempting a door
            if (theString.equalsIgnoreCase("Up")) {
                return (getMyCurrentRow() - 1) >= 0;
            } else if (theString.equalsIgnoreCase("Down")) {
                return (getMyCurrentRow() + 1) < MAZE_SIZE;
            } else if (theString.equalsIgnoreCase("Left")) {
                return (getMyCurrentCol() - 1) >= 0;
            } else if (theString.equalsIgnoreCase("Right")) {
                return (getMyCurrentCol() + 1) < MAZE_SIZE;
            } else { // player cannot traverse outside the maze
                return false;
            }
        }
    }

    /**
     * Moves the player up one row.
     * @return if move was successful or why it failed
     */
    public void moveUp() {
        if (canMove("Up")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.NORTH);
            if (doorUnlocked(door)) { // unlocked door, move up
                setMyCurrentRow(getMyCurrentRow() - 1);
                gameOverSuccess(); // check whether player has escaped the maze
            } else if (doorClosed(door)) { // closed door, don't move
            } else { // locked door, prompt question
                promptQuestion(door);
            }
        } else { // prevent player from leaving maze
            setMyCurrentRow(getMyCurrentRow());
        }
    }

    /**
     * Moves the player down one row.
     * @return if move was successful or why it failed
     */
    public void moveDown() {
        if (canMove("Down")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.SOUTH);
            if (doorUnlocked(door)) { // unlocked door, move down
                setMyCurrentRow(getMyCurrentRow() + 1);
                gameOverSuccess(); // check whether player has escaped the maze
            } else if (doorClosed(door)) { // closed door, don't move
            } else { // locked door, prompt question
                promptQuestion(door);
            }
        } else { // prevent player from leaving maze
            setMyCurrentRow(getMyCurrentRow());
        }
    }

    /**
     * Moves the player left one column.
     * @return if move was successful or why it failed
     */
    public void moveLeft() {
        if (canMove("Left")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.WEST);
            if (doorUnlocked(door)) { // unlocked door, move left
                setMyCurrentCol(getMyCurrentCol() - 1);
                gameOverSuccess(); // check whether player has escaped the maze
            } else if (doorClosed(door)) { // closed door, don't move
            } else { // locked door, prompt question
                promptQuestion(door);
            }
        } else { // prevent player from leaving maze
            setMyCurrentCol(getMyCurrentCol());
        }
    }

    /**
     * Moves the player right one column.
     * @return if move was successful or why it failed
     */
    public void moveRight() {
        if (canMove("Right")) {
            Room room = getRoom(getMyCurrentRow(), getMyCurrentCol());
            Door door = room.getDoor(Direction.EAST);
            if (doorUnlocked(door)) { // unlocked door, move right
                setMyCurrentCol(getMyCurrentCol() + 1);
                gameOverSuccess(); // check whether player has escaped the maze
            } else if (doorClosed(door)) { // closed door, don't move
            } else { // locked door, prompt question
                promptQuestion(door);
            }
        } else { // prevent player from leaving maze
            setMyCurrentCol(getMyCurrentCol());
        }
    }

    /**
     * Resets all stats for a new game.
     */
    public void newGame() {
        // save old values for firePropertyChange
        final Room[][] oldMaze = myMaze;
        final int oldRow = myCurrentRow;
        final int oldCol = myCurrentCol;
        final boolean oldGameOver = myGameOver;
        final boolean oldAttemptDoor = myAttemptDoor;
        final Door oldDoor = myCurrentDoor;
        final Question oldQuestion = myQuestion;

        // replace old values with new values
        myMaze = new Room[MAZE_SIZE][MAZE_SIZE];
        myCurrentRow = 0;
        myCurrentCol = 0;
        myGameOver = false;
        myAttemptDoor = false;
        myCurrentDoor = null;
        myQuestion = new Question("", "");

        // fire property change
        myPCS.firePropertyChange(PROPERTY_GAME_OVER, oldGameOver, myGameOver);
        myPCS.firePropertyChange(PROPERTY_UPDATE_MAZE, null, null);
        myPCS.firePropertyChange(PROPERTY_TRIVIA_QUESTION, null, myQuestion);
    }

    /**
     * @author Justin Ho
     * Saves the current game state to a file.
     */
    public void saveGame(final String theFileName)
    {
        File file = new File("./GameState.txt");

        try
        {
            // create a new file with name specified
            // by the file object
            file.createNewFile();
        }

        catch(Exception e)
        {
            System.out.println("EXCEPTION, TRIVIA MAZE: " + e);
        }

        try
        {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            // Serialize objects and write them to the file
            objectStream.writeObject(myMaze);
            objectStream.writeObject(myCurrentRow);
            objectStream.writeObject(myCurrentCol);
            objectStream.writeObject(myGameOver);
            objectStream.writeObject(myQuestion);
            objectStream.close();
            fileStream.close();

            myPCS.firePropertyChange(PROPERTY_SAVE, null, myMaze);


        }
        catch (IOException e) {
            throw new IllegalArgumentException("FAILED TO SAVE GAME, TRIVIA MAZE CLASS" + " " + e);
        }
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