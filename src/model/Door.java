package model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Door class for Trivia Maze Fall 2023 Team 2.
 * @author Viktoria Dolojan
 */
public class Door implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /* Door number. */
    private final int myDoorNumber = 0;

    /* Status of whether door is open or not. */
    private boolean myIsOpen = false;

    /* The trivia question assigned to this Door. */
    private String myQuestion = null;

    /* The answer to the trivia question. */
    private String myAnswer = null;

    /**
     * Constructs a Door object.
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
     * Return the door number.
     * @return number associated with door
     */
    public int getDoorNumber() {
        return myDoorNumber;
    }

    /**
     * Returns the status of the door.
     * @return true if door is open
     */
    public boolean getStatus() {
        return myIsOpen;
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
     * Open door if question is answered correctly.
     */
    public void openDoor() {
        myIsOpen = true;
    }

    @Override
    public String toString() {
        return "Door #" + myDoorNumber;
    }
}