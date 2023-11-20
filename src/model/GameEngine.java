package model;

import controller.Maze;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import view.GameFrame;
import view.Question;
import view.QuestionAnswer;
import view.QuestionPanel;


/**
 * Class that operates a Trivia Maze game.
 * @author Rick Adams
 * @author Viktoria Dolojan
 * @version Fall 2023
 * Trivia Maze - Team 2
 */
public class GameEngine implements PropertyChangeListener, Serializable {

//================Constants===================//

    private static final String PREVIOUS = "Nothing previous";

//================Fields=====================//
//    private final PropertyChangeSupport myPCS;
    private final QuestionAnswer myQA;
    private QuestionPanel myQuestionPanel;
    private final GameFrame myGFrame = new GameFrame();
    private Question myQuestion;
    private final Room myRoom;

    private final Maze myMaze;

    private final Door myDoor;

    private boolean myRunningGame;

    /**
     * Default constructor.
     */
    public GameEngine(final QuestionAnswer theQA,
                      final QuestionPanel theQP,
                      final Question theQuestion,
                      final Room theRoom,
                      final Door theDoor) throws SQLException {
        super();
        this.myQA = theQA;
        this.myQuestionPanel = theQP;
//        this.myQuestion = theQuestion;
        this.myRoom = theRoom;
        this.myDoor = theDoor;
        myMaze = new Maze();
        myQuestionPanel = (QuestionPanel) myGFrame.getQuestionPanel();
//        myPCS = new PropertyChangeSupport(this);
//        myQuestionPanel.addPropertyChangeListener(this);
//
//        myQuestionPanel.addPropertyChangeListener(myRoom);
//        myQuestionPanel.addPropertyChangeListener(myDoor);
        showNextQuestion();
    }


    public void setMyQuestion(Question theQuestion) {
        Question oldQuestion = this.myQuestion;
        this.myQuestion = theQuestion;
//        myPCS.firePropertyChange("myQuestion", oldQuestion, theQuestion);
    }

    private void showNextQuestion() throws SQLException {
        final List<Map<String, String>> questions = myQA.getQuestions();
        if (!questions.isEmpty()) {
            Map<String, String> questionData = questions.remove(0);
            myQuestion = myQA.createQuestion(questionData.get("theType"),
                                             questionData.get("param1"),
                                             questionData.get("param2"));
            myQuestionPanel.displayQuestion(myQuestion);

//            myQuestionPanel.firePropertyChange("myQuestion", PREVIOUS, myQuestion);

        } else {
            myRunningGame = false; // End the game
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
        if ("myQuestion".equals(evt.getPropertyName())) {
            final String newQuestion = (String) evt.getNewValue();
            if (newQuestion != null) {
                // Handle the non-null value
            } else {
                // Handle the case where newQuestion is null
            }
        }
    }
}
