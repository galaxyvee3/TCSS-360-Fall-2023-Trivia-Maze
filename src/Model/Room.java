package Model;

import java.util.Random;

/**
 * Room class for Trivia Maze, Team 2.
 * @author rick_adams.
 * @author Viktoria Dolojan.
 */
public class Room {
    private boolean myQuestionAnsweredCorrectly;
    private final boolean myClueStatus;
    private String myClueContent;

    /** Object of the Door class. */
    private final Door myDoor;

    private static final Random RANDOM = new Random();

    public Room() {
        myDoor = new Door();
        myClueStatus = RANDOM.nextBoolean();
        if (myClueStatus) {
            myClueContent = generateClueContent();
        }
    }

    public void answerTriviaQuestion(final String thePlayerAnswer) {
        // Check if the player's answer is correct and update the state accordingly.
        final String correctAnswer = "Your correct answer"; // Replace with the actual correct answer.

        if (thePlayerAnswer.equals(correctAnswer)) {
            myQuestionAnsweredCorrectly = true; // The player answered correctly.
            myDoor.openDoor(); // Open the door if the answer is correct.
        } else {
            myQuestionAnsweredCorrectly = false; // The player answered incorrectly.
        }
    }

//  Probably obsolete.
//    public boolean isDoorLocked() {
//        return myDoor.getStatus();
//    }

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
