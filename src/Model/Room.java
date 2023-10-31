package Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Room class for Trivia Maze, Team 2.
 * @author rick_adams.
 * @author Viktoria Dolojan.
 * @author Justin Ho
 */

public class Room extends JPanel implements KeyListener {
    private final int myNumDoors;
    private boolean myDoorLocked;
    private boolean myQuestionAnsweredCorrectly;

    private static final int MAX_DOORS = 2;

    private static final Random RANDOM = new Random();

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
            // More door selection, as needed.
            repaint();
        }
    }

    private void handleDoorSelection (int selectDoor) {
        // Handle what happens when user select door
        // Display Trivia Question and wait for user answers
        displayTriviaQuestion();
    }

    private void displayTriviaQuestion() {
        // Display Questions using UI Components
        // JLabel to display Questions
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
        final String correctAnswer = "Your correct answer";
        if (theAnswer.equals(correctAnswer)) {
            myQuestionAnsweredCorrectly = true; // The player answered correctly.
            myDoorLocked = false; // Unlock the door.
        } else {
            myQuestionAnsweredCorrectly = false; // The player answered incorrectly.
        }
        repaint();
    }

    public boolean getIsDoorLocked() {
        return myDoorLocked;
    }

    public boolean getIsQuestionAnsweredCorrectly() {
        return myQuestionAnsweredCorrectly;
    }
}
