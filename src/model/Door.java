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
    private int myDoorNumber = 0;

    /* Status of whether door is open or not. */
    private boolean myIsOpen = false;

    /* The trivia question assigned to this Door. */
    private String myQuestion = null;

    /* The answer to the trivia question. */
    private String myAnswer = null;

    /* First room the door is connected to. */
    private Room myRoom1 = null;

    /* Second room the door is connected to. */
    private Room myRoom2 = null;

    /* Direction of the door to relative to Room 1. */
    private Direction myDirection1 = null;

    /* Direction of the door to relative to Room 2. */
    private Direction myDirection2 = null;

    /**
     * Constructs a Door object.
     * @param theNumber door number
     */
    public Door(final int theNumber) {
        myDoorNumber = theNumber;
    }

    /**
     * Constructs a Door object.
     * @param theNumber door number
     * @param theRoom1 first room connected by door
     * @param theRoom2 second room connected by door
     * @param theDirection1 direction of door relative to room 1
     * @param theDirection2 direction of door relative to room 2
     */
    public Door(final int theNumber, final Room theRoom1, final Room theRoom2,
                final Direction theDirection1, final Direction theDirection2) {
        myDoorNumber = theNumber;
        myRoom1 = theRoom1;
        myRoom2 = theRoom2;
        myDirection1 = theDirection1;
        myDirection2 = theDirection2;
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
     * Returns the first room connected to the door.
     * @return room object
     */
    public Room getRoom1() {
        return myRoom1;
    }

    /**
     * Returns the second room connected to the door.
     * @return room object
     */
    public Room getRoom2() {
        return myRoom2;
    }

    /**
     * Returns the direction of the door relative to room 1.
     * @return cardinal direction
     */
    public Direction getDirection1() {
        return myDirection1;
    }

    /**
     *
     * @return cardinal direction
     */
    public Direction getDirection2() {
        return myDirection2;
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