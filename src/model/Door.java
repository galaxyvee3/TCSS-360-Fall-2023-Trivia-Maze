package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.io.Serializable;

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

//=====================Fields==========================//
    /** The first connected Room. */
    final private Room myRoom1;

    /** The second connected Room. */
    final private Room myRoom2;

    /** Direction of Door relative to Room 1. */
    final private Direction myDir1;

    /** Direction of Door relative to Room 2. */
    final private Direction myDir2;

    /** Status of whether door is unlocked or not. */
    private boolean myUnlocked;

    /** Status of whether door is closed forever because player answered incorrectly. */
    private boolean myClosed;

    /** The trivia question assigned to this Door. */
    private Question myQuestion;

    /** Keeps track of whether this door is currently being attempted by the player. */
    private boolean myBeingAttempted;

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
        myBeingAttempted = false;
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
    public Question getQuestion() {
        return myQuestion;
    }

    /**
     * Get the current door that is being attempted.
     * @return current door
     */
    public boolean getAttempting() {
        return myBeingAttempted;
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

    /**
     * Assigns a trivia question to the door.
     * @param theQuestion the trivia question
     */
    public void setQuestion(final Question theQuestion) {
        myQuestion = theQuestion;
    }

    /**
     * Set whether the door is being attempted by the player.
     * @param theAttempt true if door is being attempted
     */
    public void setAttempting(final boolean theAttempt) {
        myBeingAttempted = theAttempt;
    }

    @Override
    public String toString() {
        String status = "";
        if (myUnlocked) {
            status = "unlocked";
        } else if (myClosed) {
            status = "closed";
        } else {
            status = "locked";
        }
        return "\nDoor:" + status +
                "\n1 " + myRoom1.toString() + ", " + myDir1 +
                "\n2 " + myRoom2.toString() + ", " + myDir2;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
    }
}