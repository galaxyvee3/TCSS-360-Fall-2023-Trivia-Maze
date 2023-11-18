package model;

import java.io.*;

/**
 * Door object in the maze.
 * @author Viktoria Dolojan
 * @author Justin Ho
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class Door implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
        return "Door init";
    }
}