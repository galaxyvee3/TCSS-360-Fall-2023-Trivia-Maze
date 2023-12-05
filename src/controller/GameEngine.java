package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import model.Door;
import model.Maze;
import model.Room;
import view.*;


/**
 * Class that operates a Trivia Maze game.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class GameEngine implements PropertyChangeListener, ActionListener {
    // Constants
    private static final int TIMER_DELAY = 100;

    // Fields
    private final QuestionAnswer myQA;
    private final GameFrame myGFrame;
    private final Room myRoom;
    private final Maze myMaze;
    private final Door myDoor;
    private boolean myRunningGame;

    private PropertyChangeEvent myPcs;
    private final Timer myTimer;

    private MazePanel myMazePanel;

    public GameEngine(QuestionAnswer theQA, Room theRoom, Door theDoor) {
        this.myQA = theQA;
        this.myRoom = theRoom;
        this.myDoor = theDoor;
        this.myMaze = new Maze();
        this.myGFrame = new GameFrame();
        myMazePanel = new MazePanel(myMaze);
        this.myRunningGame = true;
//        this.myPcs = new PropertyChangeEvent();
        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(new GameLoop(), 0, TIMER_DELAY);
    }

    @Override
    public void actionPerformed(ActionEvent theEvent) {
        // No need to handle QuestionPanel interaction for now
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes if needed
    }

    public class GameLoop extends TimerTask {
        @Override
        public void run() {
            if (myRunningGame) {
                checkPlayerInteraction();
                checkQuestionAnswered();
                myGFrame.getMazePanel().repaint();
                myGFrame.render();
            } else {
                myTimer.cancel();
            }
        }

        private void checkPlayerInteraction() {
            // Placeholder for checking player interactions
            // Update the game state accordingly
        }

        private void checkQuestionAnswered() {
            // Placeholder for checking if a question is answered
            processAnswer();
        }
    }

    private void processAnswer() {
        String userAnswer = "User's Answer"; // Replace this with the actual user's answer

        for (Map<String, String> questionData : myQA.getQuestions()) {
            String correctAnswer = questionData.get("ANSWER");

            if (correctAnswer.equals(userAnswer)) {
                handleCorrectAnswer();
                return;  // Exit the loop if the correct answer is found
            }
        }

        // If the loop completes without finding a correct answer, handle an incorrect answer
        handleIncorrectAnswer();
    }

    private void handleCorrectAnswer() {
        if (myQA.hasMoreQuestions()) {
            Map<String, String> nextQuestionData = myQA.getNextQuestion();
            String questionText = nextQuestionData.get("QUESTION");
            String answerText = nextQuestionData.get("ANSWER");
            // Use a default constructor for QuestionAnswer
            QuestionAnswer nextQuestion = new QuestionAnswer();
//            myGFrame.getQuestionPanel().setCurrentQuestion(nextQuestion);
        } else {
            myRunningGame = false;
//            myGFrame.getQuestionPanel().setCQuestion("Game Over!");
        }
    }

    private void handleIncorrectAnswer() {
//        myGFrame.getQuestionPanel().setFeedback("Incorrect answer. Try again.");
    }

    public void setRunningGame(boolean runningGame) {
        this.myRunningGame = runningGame;
    }
}