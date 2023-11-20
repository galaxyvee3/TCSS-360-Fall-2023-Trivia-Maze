package model;

import view.Question;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    /** Status of whether door is unlocked or not. */
    private boolean myUnlocked = true;

    /** The trivia question assigned to this Door. */
    private String myQuestion = null;

    /** The answer to the trivia question. */
    private String myAnswer = null;

    /** Status of whether door is closed forever because player answered incorrectly. */
    private boolean myClosed = false;
    private Question associatedQuestion;

    public static void main (String[] args) {
        Door door = new Door(); // Create instance of object Class
        try (FileOutputStream fileOut = new FileOutputStream("door.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(door); // Serialize door object to room.ser

            System.out.println("Door object has been serialized!\n" +
                    "Data before serialization");
        } catch (
                IOException e
        ) {
            e.printStackTrace();

        }
        // Deserialization
        try (FileInputStream fileIn = new FileInputStream("door.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            door = (Door) objectIn.readObject(); // Deserialize the Maze object
            System.out.println("Door object deserialized!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


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
        if ("myQuestion".equals(theEvent.getPropertyName())) {
            Object newValue = theEvent.getNewValue();
            if (newValue instanceof String newQuestion) {
                // Assume the newValue is the new question


            }
        }
    }
}