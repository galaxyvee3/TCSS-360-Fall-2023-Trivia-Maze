package model;

import controller.Maze;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.logging.Logger;
import java.io.*;

/**
 * Room object in the maze.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @author Justin Ho
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class Room implements PropertyChangeListener, Serializable {
//
//    @Serial
//    private static final long serialVersionUID =
//            2L;
//    public static void main (String[] args) {
//        Room room = new Room(); // Create instance of object Class
//        try (FileOutputStream fileOut = new FileOutputStream("room.ser");
//             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
//
//            objectOut.writeObject(room); // Serialize room object to room.ser
//
//            System.out.println("Room object has been serialized!\n" +
//                    "Data before serialization");
//        }
//        catch (
//                IOException e
//        ){
//            e.printStackTrace();
//
//        }
//        // Deserialization
//        try (FileInputStream fileIn = new FileInputStream("room.ser");
//             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
//
//            room = (Room)objectIn.readObject(); // Deserialize the Maze object
//            System.out.println("Room object deserialized!");
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }

//==================Constants=======================//
    private static final Logger LOGGER = Logger.getLogger(Room.class.getName());

    /** Random object. */
    private static final Random RANDOM = new Random();

    private static final int EDGE_COUNT = 2;

    private static final int INNER_COUNT = 4;

//==================Fields=====================//

    /** The row of the Room in the Maze. */
    private int myRow;

    private  int myRowCnt;

    /** The column of the Room in the Maze. */
    private int myColumn;

    private int myColCnt;
    
    /** Boolean for whether the door is locked. */
    private boolean myDoorLocked;

    /** Boolean for whether the user answers the question correctly. */
    private boolean myCorrectAnswer;

    /** Boolean for whether there is a clue available. */
    private boolean myClueStatus;

    /** The clue for the trivia question. */
    private String myClueContent;

    private ClueManager myCM;

    private Door myDoorList;
    /**
     * Default constructor.
     */
    public Room() {
        myCM = new ClueManager();
        initializeState();
        initializeClue();
        generateClueContent();
    }

    /**
     * Constructs a Room object.
     * @param theRow row of Room in maze
     * @param theColumn column of Room in maze
     */
    public Room(final int theRow,
                final int theColumn,
                final int theRowCnt,
                final int theColCnt,
                final Door theDoor) {
        initializePosition(theRow, theColumn, theRowCnt, theColCnt);
        this.myDoorList = theDoor;
    }

    /**
     * Retrieve the current trivia question from the door.
     * @param theDoor the current door the user is trying to get through
     */
    public String retrieveQuestion(final Door theDoor) {
        return theDoor.getQuestion();
    }

    /**
     * Retrieve the current answer for the trivia question from the door.
     * @param theDoor the current door the user is trying to get through
     */
    public String retrieveAnswer(final Door theDoor) {
        return theDoor.getAnswer();
    }

    /**
     * Takes the user's answer and opens the door if it is correct or block the door otherwise.
     * @param thePlayerAnswer the user answer for the trivia question
     */
    public void answerTriviaQuestion(final String thePlayerAnswer) {
        // Check if the player's answer is correct and update the state accordingly.
        final String correctAnswer = "Your correct answer";
        if (thePlayerAnswer.equals(correctAnswer)) {
            myCorrectAnswer = true; // The player answered correctly.
            myDoorLocked = false; // Door status
        } else {
            myCorrectAnswer = false; // The player answered incorrectly.
            myDoorLocked = true;
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
        return myCorrectAnswer;
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
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = myCM.getClues();
        } else {
            myClueContent = "No bonus item present.";
        }
        return myClueContent;
    }

    /**
     *
     * @return
     */
    private boolean isOnEdgeOfMaze() {
        // Check if the room is on the edge of the maze
        return myRow == 0
               || myRow == myRowCnt - 1
               || myColumn == 0
               || myColumn == myColCnt - 1;
    }

    /**
     *
     * @return
     */
    private int doorCounter() {
        final int doorCount;
        if (isOnEdgeOfMaze()) {
            doorCount = EDGE_COUNT;
        } else {
            doorCount = INNER_COUNT;
        }
        return doorCount;
    }

    /**
     *
     * @return
     */
    public int getDoorCount() {
        return doorCounter();
    }
    private void initializePosition(final int theRow,
                                    final int theColumn,
                                    final int theRowCnt,
                                    final int theColCnt) {
        myRow = theRow;
        myColumn = theColumn;
        myRowCnt = theRowCnt;
        myColCnt = theColCnt;
    }

    /**
     *
     */
    private void initializeState() {
        myDoorLocked = true;
        myCorrectAnswer = false;
    }

    /**
     *
     */
    private void initializeClue() {
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = generateClueContent();
        }
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public String toString() {
        return "Room init";
    }
}