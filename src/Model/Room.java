package Model;

import java.util.Random;

/**
 * Room class for Trivia Maze, Team 2.
 * @author rick_adams.
 * @author Viktoria Dolojan.
 */
public class Room {
    private boolean myDoorLocked;
    private boolean myQuestionAnsweredCorrectly;
    private final boolean myClueStatus;
    private String myClueContent;
    private static final int MAX_DOORS = 2;
    private static final Random RANDOM = new Random();

    public Room() {
        int myNumDoors = RANDOM.nextInt(MAX_DOORS) + 1;
        myDoorLocked = true;
        myQuestionAnsweredCorrectly = false;
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = generateClueContent();
        }
    }

    public void answerTriviaQuestion(String playerAnswer) {
        // Check if the player's answer is correct and update the state accordingly.
        String correctAnswer = "Your correct answer";
        if (playerAnswer.equals(correctAnswer)) {
            myQuestionAnsweredCorrectly = true; // The player answered correctly.
            myDoorLocked = false; // Unlock the door.
        } else {
            myQuestionAnsweredCorrectly = false; // The player answered incorrectly.
        }
    }

    public boolean isDoorLocked() {
        return myDoorLocked;
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

    private String generateClueContent() {
       //TODO: Install mechanics to generate the clue!
        return "This is a clue to help you answer the trivia question.";
    }
}
