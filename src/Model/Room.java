package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Room class for Trivia Maze, Team 2.
 * @author rick_adams.
 * @author Viktoria Dolojan.
 */
public class Room extends JPanel implements KeyListener {
    private final int myNumDoors;
    private boolean myDoorLocked;
    private boolean myQuestionAnsweredCorrectly;

    private static final int MAX_DOORS = 2;

    private static final int CORRECT_DOOR_NUMBER = 0; // Replace with the actual correct door number.

    private static final Random RANDOM = new Random();

    /**
     * Public constructor.
     */
    public Room() {
        super();
        myNumDoors = RANDOM.nextInt(MAX_DOORS) + 1;
        myDoorLocked = true;
        myQuestionAnsweredCorrectly = false;
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyTyped(final KeyEvent theE) {
        // Handle user input for door selection only if the door is not locked.
        if (!myDoorLocked) {
            int doorNumber = theE.getKeyChar() - '1';
            if (doorNumber >= 0 && doorNumber < myNumDoors) {
                if (doorNumber == CORRECT_DOOR_NUMBER) {
                    // Player selected the correct door.
                    myDoorLocked = false; // Unlock the door.
                }
                // More door selection, as needed.
            }
            // Repaint the room.
            repaint();
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        // Handle key press events (if needed).
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        // Handle key release events (if needed).
    }

    public void answerTriviaQuestion(final String theAnswer) {
        // Check if the player's answer is correct and update the state accordingly.
        // Replace with the actual correct answer.
        String myCorrectAnswer = "Your correct answer";
        if (theAnswer.equals(myCorrectAnswer)) {
            myQuestionAnsweredCorrectly = true; // The player answered correctly.
            myDoorLocked = false; // Unlock the door.
        } else {
            myQuestionAnsweredCorrectly = false; // The player answered incorrectly.
        }
        repaint();
    }
    /**
     * Public accessor method for the door lock status.
     * @return Returns the door lock status.
     */
    public boolean getDoorLockStatus() {
        return myDoorLocked;
    }
    /**
     * Public accessor method for the correct answers.
     * @return Returns the door lock status.
     */
    public boolean getQuestionAnsweredCorrectly() {
        return myQuestionAnsweredCorrectly;
    }
}
