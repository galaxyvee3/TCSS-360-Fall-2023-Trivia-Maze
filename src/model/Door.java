package model;

import view.Question;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;

/**
 * Door object in the maze.
 * @author Viktoria Dolojan
 * @author Rick Adams
 * @author Justin Ho
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class Door implements Serializable, PropertyChangeListener {
    @Serial
    private static final long serialVersionUID = 1L;

    /** Property name for updating the doors when door has changed status. */
    public static final String PROPERTY_UPDATE_DOORS = "Update doors";

    /** Property change support for the class. */
    private final PropertyChangeSupport myPCS;

    /** The first connected Room. */
    private Room myRoom1;

    /** The second connected Room. */
    private Room myRoom2;

    /** Direction of Door relative to Room 1. */
    private Direction myDir1;

    /** Direction of Door relative to Room 2. */
    private Direction myDir2;

    /** Status of whether door is unlocked or not. */
    private boolean myUnlocked;

    /** Status of whether door is closed forever because player answered incorrectly. */
    private boolean myClosed;

    /** The trivia question assigned to this Door. */
    private String myQuestion;

    /** The answer to the trivia question. */
    private String myAnswer;

    private Question associatedQuestion;

    /**
     * Default constructor.
     */
    public Door(final Room theRoom1, final Room theRoom2,
                final Direction theDir1, final Direction theDir2) {
        myRoom1 = theRoom1;
        myRoom2 = theRoom2;
        myDir1 = theDir1;
        myDir2 = theDir2;
        myUnlocked = false;
        myClosed = false;
        myQuestion = null;
        myAnswer = null;
        myPCS = new PropertyChangeSupport(this);
        // add door to rooms
        myRoom1.addDoor(myDir1, this);
        myRoom2.addDoor(myDir2, this);
    }

    /**
     * Get the first Room the Door is connected to.
     * @return first Room
     */
    public Room getRoom1() {
        return myRoom1;
    }

    /**
     * Get the second Room the Door is connected to.
     * @return second Room
     */
    public Room getRoom2() {
        return myRoom2;
    }

    /**
     * Get Direction 1 of Door.
     * @return Direction 1
     */
    public Direction getMyDir1() {
        return myDir1;
    }

    /**
     * Get Direction 2 of Door.
     * @return Direction 2
     */
    public Direction getMyDir2() {
        return myDir2;
    }

    /**
     * Get the unlock status of the door.
     * @return true if door is unlocked
     */
    public boolean getUnlocked() {
        return myUnlocked;
    }

    /**
     * Get the close status of the door.
     * @return true if door is closed forever
     */
    public boolean getClosed() {
        return myClosed;
    }

    /**
     * Get the trivia question assigned to the door.
     * @return the trivia question
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Get the answer to the trivia question.
     * @return the answer
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Unlock door if question is answered correctly.
     * Fire PCS for GUI to update doors.
     */
    public void unlockDoor() {
        myUnlocked = true;
        myPCS.firePropertyChange(PROPERTY_UPDATE_DOORS, true, true);
    }

    /**
     * Close door forever if player answers incorrectly.
     * Fire PCS for GUI to update doors.
     */
    public void closeDoor() {
        myClosed = true;
        myPCS.firePropertyChange(PROPERTY_UPDATE_DOORS, true, true);
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
     * Set the associated question for this door.
     * @param question The question to be associated with this door.
     */
    public void setAssociatedQuestion(final Question question) {
        this.associatedQuestion = question;
    }

    /**
     * Get the associated question for this door.
     * @return The associated question for this door.
     */
    public Question getAssociatedQuestion() {
        return associatedQuestion;
    }

    @Override
    public String toString() {
        return "Door init";
    }

    /**
     * This method gets called when a bound property is changed.
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        /*if ("myQuestion".equals(theEvent.getPropertyName())) {
            Object newValue = theEvent.getNewValue();
            if (newValue instanceof String newQuestion) {
                // Assume the newValue is the new question


            }
        }*/
    }
}