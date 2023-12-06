package controller;

import model.Door;
import model.Maze;
import model.Room;
import view.GameFrame;
import view.MazePanel;
import view.QuestionAnswer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that operates a Trivia Maze game.
 * @author Rick Adams.
 * @author Viktoria Dolojan
 * @version Fall 2023.
 * Trivia Maze - Team 2.
 */
public class GameEngine implements PropertyChangeListener, ActionListener {
    //=====================Constants==========================//
    /** Constant int for loop delay. */
    private static final int TIMER_DELAY = 100;

    private static final String ANSWER = "ANSWER";
    /** Constant Timer object. */
    private static final Timer TIMER = new Timer();
    //=====================Fields==========================//
    /** QuestionAnswer class object. */
    private final QuestionAnswer myQA;
    /** GameFrame class object. */
    private final GameFrame myGFrame;
    /** Room class object. */
    private final Room myRoom;
    /** Maze class object. */
    private final Maze myMaze;
    /** Door class object. */
    private final Door myDoor;
    /** Game status boolean. */
    private boolean myRunningGame;
    /** MazePanel class object. */
    private MazePanel myMazePanel;

    /**
     * Public constructor.
     * @param theQA QuestionAnswer object.
     * @param theRoom Room object.
     * @param theDoor Door object.
     */
    public GameEngine(final QuestionAnswer theQA,
                      final Room theRoom,
                      final Door theDoor) {
        this.myQA = theQA;
        this.myRoom = theRoom;
        this.myDoor = theDoor;
        this.myMaze = new Maze();
        this.myGFrame = new GameFrame();
        myMazePanel = new MazePanel(myMaze);
        this.myRunningGame = true;
        TIMER.scheduleAtFixedRate(new GameLoop(), 0, TIMER_DELAY);
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        // No need to handle QuestionPanel interaction for now
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        // Handle property changes if needed
    }

    /*
    private void processAnswer() {
        // Replace this with the actual user's answer.
        final String userAnswer = "User's Answer";

        for (Map<String, String> questionData : myQA.getQuestions()) {
            final String correctAnswer = questionData.get(ANSWER);
            if (correctAnswer.equals(userAnswer)) {
                handleCorrectAnswer();
                return;  // Exit the loop if the correct answer is found.
            }
        }
        // Otherwise, handle an incorrect answer.
        handleIncorrectAnswer();
    }

    private void handleCorrectAnswer() {
        if (myQA.hasMoreQuestions()) {
            final Map<String, String> nextQuestionData = myQA.getNextQuestion();
            final String questionText = nextQuestionData.get("QUESTION");
            final String answerText = nextQuestionData.get(ANSWER);
            // Use a default constructor for QuestionAnswer
            final QuestionAnswer nextQuestion = new QuestionAnswer();
//            myGFrame.getQuestionPanel().setCurrentQuestion(nextQuestion);
        } else {
            myRunningGame = false;
           //myGFrame.getQuestionPanel().setCQuestion("Game Over!");
        }
    }
    */

    private void handleIncorrectAnswer() {
    }

    public void setRunningGame(final boolean theRunningGame) {
        this.myRunningGame = theRunningGame;
    }

    /**
     * Private game loop class.
     */
    public class GameLoop extends TimerTask {

        /**
         * Method that starts the game.
         */
        @Override
        public void run() {
            if (myRunningGame) {
                checkPlayerInteraction();
                checkQuestionAnswered();
                myGFrame.getMazePanel().repaint();
                myGFrame.render();
            } else {
                TIMER.cancel();
            }
        }

        private void checkPlayerInteraction() {
            // Placeholder for checking player interactions
            // Update the game state accordingly
        }

        private void checkQuestionAnswered() {
            // Placeholder for checking if a question is answered
            //processAnswer();
        }
    }
}
