package Model;

import java.util.Map;
import java.util.Random;

/**
 * Room class for Trivia Maze, Team 2.
 * @author rick_adams
 * @author Viktoria Dolojan
 * @author Justin Ho
 */
public class Room {
    /* Map of Doors contained in the Room. */
    private Map<Direction, Door> myDoors;

    /* The row of the Room in the Maze. */
    private int myRow;

    /* The column of the Room in the Maze. */
    private int myColumn;

    /* Boolean for whether the door is locked. */
    private boolean myDoorLocked;

    /* Boolean for whether the user answers the question correctly. */
    private boolean myQuestionAnsweredCorrectly;

    /* Boolean for whether there is a clue available. */
    private final boolean myClueStatus;

    /* The clue for the trivia question. */
    private String myClueContent;

    /* Random object. */
    private static final Random RANDOM = new Random();

    /**
     * Constructs a Room object.
     */
    public Room() {
        myDoorLocked = true;
        myQuestionAnsweredCorrectly = false;
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = generateClueContent();
        }
    }

    /**
     * Constructs a Room object.
     * @param theRow row of Room in maze
     * @param theColumn column of Room in maze
     */
    public Room(final int theRow, final int theColumn) {
        myRow = theRow;
        myColumn = theColumn;
        myDoorLocked = true;
        myQuestionAnsweredCorrectly = false;
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = generateClueContent();
        }
    }

    /**
     * Takes the user's answer and opens the door if it is correct or block the door otherwise.
     * @param playerAnswer the user answer for the trivia question
     */
    public void answerTriviaQuestion(String playerAnswer) {
        // Check if the player's answer is correct and update the state accordingly.
        String correctAnswer = "Your correct answer";
        if (playerAnswer.equals(correctAnswer)) {
            myQuestionAnsweredCorrectly = true; // The player answered correctly.
            myDoorLocked = false; // Unlock the door.
        } else {
            myQuestionAnsweredCorrectly = false; // The player answered incorrectly.
        }
    }

    /**
     * Returns whether the door is locked.
     * @return true if door is locked
     */
    public boolean isDoorLocked() {
        return myDoorLocked;
    }

    /**
     * Returns whether the user answered the question correctly.
     * @return true if the user answered the question correctly
     */
    public boolean isQuestionAnsweredCorrectly() {
        return myQuestionAnsweredCorrectly;
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
       //TODO: Install mechanics to generate the clue!
        return "This is a clue to help you answer the trivia question.";
    }
}