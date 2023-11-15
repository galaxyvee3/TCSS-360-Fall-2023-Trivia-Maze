package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Door object in the maze.
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class Door implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** Status of whether door is unlocked or not. */
    private boolean myUnlocked = false;

    /** The trivia question assigned to this Door. */
    private String myQuestion = null;

    /** The answer to the trivia question. */
    private String myAnswer = null;

    /** Status of whether door is closed forever because player answered incorrectly. */
    private boolean myClosed = false;

    /**
     * Default constructor.
     */
    public Door() {
    }

    /**
     * Return the trivia question assigned to the door.
     * @return the trivia question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Return the answer to the trivia question.
     * @return the answer
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Returns the unlock status of the door.
     * @return true if door is unlocked
     */
    public boolean getUnlocked() {
        return myUnlocked;
    }

    /**
     * Returns the close status of the door.
     * @return true if door is closed forever
     */
    public boolean getClosed() {
        return myClosed;
    }

    /**
     * Assigns a trivia question to the door.
     * @param theQuestion the trivia question
     */
    public void setQuestion(final String theQuestion) {
        myQuestion = theQuestion;
    }

    /**
     * Assigns the answer of the trivia question.
     * @param theAnswer the answer
     */
    public void setAnswer(final String theAnswer) {
        myAnswer = theAnswer;
    }

    /**
     * Unlock door if question is answered correctly.
     */
    public void unlockDoor() {
        myUnlocked = true;
    }

    /**
     * Close door forever if player answers incorrectly.
     */
    public void closeDoor() {
        myClosed = true;
    }

    @Override
    public String toString() {
        return "Unlocked: " + myUnlocked;
    }
}