package model;

import java.util.Random;

/**
 * Room class for Trivia Maze, Team 2.
 * @author rick_adams.
 * @author Viktoria Dolojan.
 */
public class Room {
    private boolean myQuestionAnsweredCorrectly;
    private final Door myDoor;
    private final boolean myClueStatus;
    private String myClueContent;
    private static final Random RANDOM = new Random();

    public Room() {
        myDoor = new Door();
        myQuestionAnsweredCorrectly = false;
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = "This is a clue to help you answer the trivia question.";
        }
    }

    public void answerTriviaQuestion(String playerAnswer) {
        // Check if the player's answer is correct and update the state accordingly.
        String correctAnswer = "Your correct answer"; // Replace with the actual correct answer.

        if (playerAnswer.equals(correctAnswer)) {
            myQuestionAnsweredCorrectly = true; // The player answered correctly.
            myDoor.openDoor(); // Open the door if the answer is correct.
        } else {
            myQuestionAnsweredCorrectly = false; // The player answered incorrectly.
        }
    }

    public boolean isDoorLocked() {
        return myDoor.getStatus();
    }

    public boolean isQuestionAnsweredCorrectly() {
        return myQuestionAnsweredCorrectly;
    }

    public boolean isCluePresent() {
        return myClueStatus;
    }

    public String getMyClueContent() {
        return myClueContent;
    }
}

